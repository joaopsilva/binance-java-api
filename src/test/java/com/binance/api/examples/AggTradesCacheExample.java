package com.binance.api.examples;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.market.AggTrade;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Illustrates how to use the aggTrades event stream to create a local cache of trades for a symbol.
 */
public class AggTradesCacheExample {

  /**
   * Key is the aggregate trade id, and the value contains the aggregated trade data, which is
   * automatically updated whenever a new agg data stream event arrives.
   */
  private Map<Long, AggTrade> aggTradesCache;

  public AggTradesCacheExample(String symbol) {
    initializeAggTradesCache(symbol);
    startAggTradesEventStreaming(symbol);
  }

  /**
   * Initializes the aggTrades cache by using the REST API.
   */
  private void initializeAggTradesCache(String symbol) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
    BinanceApiRestClient client = factory.newRestClient();
    List<AggTrade> aggTrades = client.getAggTrades(symbol.toUpperCase());

    this.aggTradesCache = new HashMap<>();
    for (AggTrade aggTrade : aggTrades) {
      aggTradesCache.put(aggTrade.getAggregatedTradeId(), aggTrade);
    }
  }

  /**
   * Begins streaming of agg trades events.
   */
  private void startAggTradesEventStreaming(String symbol) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
    BinanceApiWebSocketClient client = factory.newWebSocketClient();

    client.onAggTradeEvent(symbol.toLowerCase(), response -> {
      Long aggregatedTradeId = response.getAggregatedTradeId();
      AggTrade updateAggTrade = aggTradesCache.get(aggregatedTradeId);
      if (updateAggTrade == null) {
        // new agg trade
        updateAggTrade = new AggTrade();
      }
      updateAggTrade.setAggregatedTradeId(aggregatedTradeId);
      updateAggTrade.setPrice(response.getPrice());
      updateAggTrade.setQuantity(response.getQuantity());
      updateAggTrade.setFirstBreakdownTradeId(response.getFirstBreakdownTradeId());
      updateAggTrade.setLastBreakdownTradeId(response.getLastBreakdownTradeId());
      updateAggTrade.setBuyerMaker(response.isBuyerMaker());

      // Store the updated agg trade in the cache
      aggTradesCache.put(aggregatedTradeId, updateAggTrade);
      System.out.println(updateAggTrade);
    });
  }

  /**
   * @return an aggTrades cache, containing the aggregated trade id as the key,
   * and the agg trade data as the value.
   */
  public Map<Long, AggTrade> getAggTradesCache() {
    return aggTradesCache;
  }

  public static void main(String[] args) {
    new AggTradesCacheExample("ETHBTC");
  }
}
