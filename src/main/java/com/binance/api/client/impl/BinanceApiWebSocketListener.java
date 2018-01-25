package com.binance.api.client.impl;

import com.binance.api.client.BinanceApiCallback;
import com.binance.api.client.exception.BinanceApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import java.io.IOException;

/**
 * Binance API WebSocket listener.
 */
public class BinanceApiWebSocketListener<T> extends WebSocketListener {

  private BinanceApiCallback<T> callback;

  private Class<T> eventClass;

  private ObjectMapper mapper;

  private boolean closing = false;

  public BinanceApiWebSocketListener(BinanceApiCallback<T> callback, Class<T> eventClass) {
    this(callback, eventClass, new ObjectMapper());
  }

  public BinanceApiWebSocketListener(BinanceApiCallback<T> callback, Class<T> eventClass, ObjectMapper mapper) {
    this.callback = callback;
    this.eventClass = eventClass;
    this.mapper = mapper;
  }

  @Override
  public void onMessage(WebSocket webSocket, String text) {
    try {
      T event = mapper.readValue(text, eventClass);
      callback.onResponse(event);
    } catch (IOException e) {
      throw new BinanceApiException(e);
    }
  }

  @Override
  public void onClosing(final WebSocket webSocket, final int code, final String reason) {
    closing = true;
  }

  @Override
  public void onFailure(WebSocket webSocket, Throwable t, Response response) {
    if (!closing) {
      callback.onFailure(t);
    }
  }
}