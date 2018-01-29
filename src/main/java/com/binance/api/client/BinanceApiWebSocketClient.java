package com.binance.api.client;

import java.util.List;
import com.binance.api.client.domain.event.AggTradeEvent;
import com.binance.api.client.domain.event.AllMarketTickersEvent;
import com.binance.api.client.domain.event.CandlestickEvent;
import com.binance.api.client.domain.event.DepthEvent;
import com.binance.api.client.domain.event.UserDataUpdateEvent;
import com.binance.api.client.domain.market.CandlestickInterval;

import java.io.Closeable;

/**
 * Binance API data streaming façade, supporting streaming of events through web sockets.
 */
public interface BinanceApiWebSocketClient extends Closeable {

  Closeable onDepthEvent(String symbol, BinanceApiCallback<DepthEvent> callback);

  Closeable onCandlestickEvent(String symbol, CandlestickInterval interval, BinanceApiCallback<CandlestickEvent> callback);

  Closeable onAggTradeEvent(String symbol, BinanceApiCallback<AggTradeEvent> callback);

  Closeable onUserDataUpdateEvent(String listenKey, BinanceApiCallback<UserDataUpdateEvent> callback);

  Closeable onAllMarketTickersEvent(BinanceApiCallback<List<AllMarketTickersEvent>> callback);
  
  void close();
}
