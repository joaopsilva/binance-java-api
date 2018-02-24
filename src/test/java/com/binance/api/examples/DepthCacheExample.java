package com.binance.api.examples;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.event.DepthEvent;
import com.binance.api.client.domain.market.OrderBook;
import com.binance.api.client.domain.market.OrderBookEntry;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/**
 * Illustrates how to use the depth event stream to create a local cache of bids/asks for a symbol.
 */
public class DepthCacheExample {

  private static final String BIDS = "BIDS";
  private static final String ASKS = "ASKS";

  private final String symbol;
  private final BinanceApiRestClient restClient;
  private final BinanceApiWebSocketClient wsClient;
  private final AtomicReference<Consumer<DepthEvent>> wsCallback = new AtomicReference<>();
  private final Map<String, NavigableMap<BigDecimal, BigDecimal>> depthCache = new HashMap<>();

  private long lastUpdateId = -1;

  public DepthCacheExample(String symbol) {
    this.symbol = symbol;

    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
    wsClient = factory.newWebSocketClient();
    restClient = factory.newRestClient();

    final List<DepthEvent> pendingDeltas = startDepthEventStreaming();

    initializeDepthCache();

    applyPendingDeltas(pendingDeltas);
  }

  /**
   * Begins streaming of depth events.
   *
   * Any events received are cached until the rest API is polled for an initial snapshot.
   */
  private List<DepthEvent> startDepthEventStreaming() {
    final List<DepthEvent> pendingDeltas = new CopyOnWriteArrayList<>();
    wsCallback.set(pendingDeltas::add);

    wsClient.onDepthEvent(symbol.toLowerCase(), event -> wsCallback.get().accept(event));

    return pendingDeltas;
  }

  /**
   * Initializes the depth cache by getting a snapshot from the REST API.
   */
  private void initializeDepthCache() {
    OrderBook orderBook = restClient.getOrderBook(symbol.toUpperCase(), 10);

    this.lastUpdateId = orderBook.getLastUpdateId();

    NavigableMap<BigDecimal, BigDecimal> asks = new TreeMap<>(Comparator.reverseOrder());
    for (OrderBookEntry ask : orderBook.getAsks()) {
      asks.put(new BigDecimal(ask.getPrice()), new BigDecimal(ask.getQty()));
    }
    depthCache.put(ASKS, asks);

    NavigableMap<BigDecimal, BigDecimal> bids = new TreeMap<>(Comparator.reverseOrder());
    for (OrderBookEntry bid : orderBook.getBids()) {
      bids.put(new BigDecimal(bid.getPrice()), new BigDecimal(bid.getQty()));
    }
    depthCache.put(BIDS, bids);
  }

  /**
   * Apply any deltas received on the web socket that have an update-id indicating they come after
   * the snapshot, that the rest client returned, was generated.
   */
  private void applyPendingDeltas(final List<DepthEvent> pendingDeltas) {
    final Consumer<DepthEvent> updateOrderBook = newEvent -> {
      if (newEvent.getFinalUpdateId() > lastUpdateId) {
        System.out.println(newEvent);
        lastUpdateId = newEvent.getFinalUpdateId();
        updateOrderBook(getAsks(), newEvent.getAsks());
        updateOrderBook(getBids(), newEvent.getBids());
        printDepthCache();
      }
    };

    final Consumer<DepthEvent> drainPending = newEvent -> {
      pendingDeltas.add(newEvent);

      pendingDeltas.stream()
          .filter(e -> e.getFinalUpdateId() > lastUpdateId) // Ignore any updates before the snapshot
          .forEach(updateOrderBook);

      wsCallback.set(updateOrderBook);
    };

    wsCallback.set(drainPending);
  }

  /**
   * Updates an order book (bids or asks) with a delta received from the server.
   *
   * Whenever the qty specified is ZERO, it means the price should was removed from the order book.
   */
  private void updateOrderBook(NavigableMap<BigDecimal, BigDecimal> lastOrderBookEntries, List<OrderBookEntry> orderBookDeltas) {
    for (OrderBookEntry orderBookDelta : orderBookDeltas) {
      BigDecimal price = new BigDecimal(orderBookDelta.getPrice());
      BigDecimal qty = new BigDecimal(orderBookDelta.getQty());
      if (qty.compareTo(BigDecimal.ZERO) == 0) {
        // qty=0 means remove this level
        lastOrderBookEntries.remove(price);
      } else {
        lastOrderBookEntries.put(price, qty);
      }
    }
  }

  public NavigableMap<BigDecimal, BigDecimal> getAsks() {
    return depthCache.get(ASKS);
  }

  public NavigableMap<BigDecimal, BigDecimal> getBids() {
    return depthCache.get(BIDS);
  }

  /**
   * @return the best ask in the order book
   */
  private Map.Entry<BigDecimal, BigDecimal> getBestAsk() {
    return getAsks().lastEntry();
  }

  /**
   * @return the best bid in the order book
   */
  private Map.Entry<BigDecimal, BigDecimal> getBestBid() {
    return getBids().firstEntry();
  }

  /**
   * @return a depth cache, containing two keys (ASKs and BIDs), and for each, an ordered list of book entries.
   */
  public Map<String, NavigableMap<BigDecimal, BigDecimal>> getDepthCache() {
    return depthCache;
  }

  /**
   * Prints the cached order book / depth of a symbol as well as the best ask and bid price in the book.
   */
  private void printDepthCache() {
    System.out.println(depthCache);
    System.out.println("ASKS:(" + getAsks().size() + ")");
    getAsks().entrySet().forEach(entry -> System.out.println(toDepthCacheEntryString(entry)));
    System.out.println("BIDS:(" + getBids().size() + ")");
    getBids().entrySet().forEach(entry -> System.out.println(toDepthCacheEntryString(entry)));
    System.out.println("BEST ASK: " + toDepthCacheEntryString(getBestAsk()));
    System.out.println("BEST BID: " + toDepthCacheEntryString(getBestBid()));
  }

  /**
   * Pretty prints an order book entry in the format "price / quantity".
   */
  private static String toDepthCacheEntryString(Map.Entry<BigDecimal, BigDecimal> depthCacheEntry) {
    return depthCacheEntry.getKey().toPlainString() + " / " + depthCacheEntry.getValue();
  }

  public static void main(String[] args) {
    new DepthCacheExample("ETHBTC");
  }
}
