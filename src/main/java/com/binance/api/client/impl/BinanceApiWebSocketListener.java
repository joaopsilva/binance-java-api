package com.binance.api.client.impl;

import java.io.IOException;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import com.binance.api.client.BinanceApiCallback;
import com.binance.api.client.exception.BinanceApiException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Binance API WebSocket listener.
 */
public class BinanceApiWebSocketListener<T> extends WebSocketListener {

  private BinanceApiCallback<T> callback;

  private Class<T> eventClass;

  private TypeReference<T> eventTypeReference;

  public BinanceApiWebSocketListener(BinanceApiCallback<T> callback, Class<T> eventClass) {
    this.callback = callback;
    this.eventClass = eventClass;
  }

  public BinanceApiWebSocketListener(BinanceApiCallback<T> callback) {
    this.callback = callback;
    this.eventTypeReference = new TypeReference<T>() {};
  }

  @Override
  public void onMessage(WebSocket webSocket, String text) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      T event = null;
      if (eventClass == null) {
        event = mapper.readValue(text, eventTypeReference);
      } else {
        event = mapper.readValue(text, eventClass);
      }
      callback.onResponse(event);
    } catch (IOException e) {
      throw new BinanceApiException(e);
    }
  }

  @Override
  public void onFailure(WebSocket webSocket, Throwable t, Response response) {
    throw new BinanceApiException(t);
  }
}