package com.binance.api.client.impl;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import com.binance.api.client.BinanceApiCallback;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.event.AggTradeEvent;
import com.binance.api.client.domain.event.AllMarketTickersEvent;
import com.binance.api.client.domain.event.CandlestickEvent;
import com.binance.api.client.domain.event.DepthEvent;
import com.binance.api.client.domain.event.UserDataUpdateEvent;
import com.binance.api.client.domain.market.CandlestickInterval;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

import java.io.Closeable;

/**
 * Binance API WebSocket client implementation using OkHttp.
 */
public class BinanceApiWebSocketClientImpl implements BinanceApiWebSocketClient, Closeable {

  private OkHttpClient client;

  public BinanceApiWebSocketClientImpl() {
    Dispatcher d = new Dispatcher();
    d.setMaxRequestsPerHost(100);
    this.client = new OkHttpClient.Builder().dispatcher(d).build();
  }

  public Closeable onDepthEvent(String symbol, BinanceApiCallback<DepthEvent> callback) {
    final String channel = String.format("%s@depth", symbol);
    return createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, DepthEvent.class));
  }

  @Override
  public Closeable onCandlestickEvent(String symbol, CandlestickInterval interval, BinanceApiCallback<CandlestickEvent> callback) {
    final String channel = String.format("%s@kline_%s", symbol, interval.getIntervalId());
    return createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, CandlestickEvent.class));
  }

  public Closeable onAggTradeEvent(String symbol, BinanceApiCallback<AggTradeEvent> callback) {
    final String channel = String.format("%s@aggTrade", symbol);
    return createNewWebSocket(channel, new BinanceApiWebSocketListener<>(callback, AggTradeEvent.class));
  }

  public Closeable onUserDataUpdateEvent(String listenKey, BinanceApiCallback<UserDataUpdateEvent> callback) {
    return createNewWebSocket(listenKey, new BinanceApiWebSocketListener<>(callback, UserDataUpdateEvent.class));
  }
  
  public Closeable onAllMarketTickersEvent(BinanceApiCallback<List<AllMarketTickersEvent>> callback) {
    final String channel = "!ticker@arr";
    return createNewWebSocket(channel, new BinanceApiWebSocketListener<List<AllMarketTickersEvent>>(callback));
  }
  
  @Override
  public void close() {
    client.dispatcher().executorService().shutdown();
  }

  private Closeable createNewWebSocket(String channel, BinanceApiWebSocketListener<?> listener) {
    String streamingUrl = String.format("%s/%s", BinanceApiConstants.WS_API_BASE_URL, channel);
    Request request = new Request.Builder().url(streamingUrl).build();
    final WebSocket webSocket = client.newWebSocket(request, listener);
    return () -> {
      final int code = 1000;
      listener.onClosing(webSocket, code, null);
      webSocket.close(code, null);
      listener.onClosed(webSocket, code, null);
    };
  } 
}
