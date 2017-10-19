package com.binance.api.client.domain.event;

/**
 * Dummy type to wrap a listen key from a server response.
 */
public class ListenKey {

  private String listenKey;

  public String getListenKey() {
    return listenKey;
  }

  public void setListenKey(String listenKey) {
    this.listenKey = listenKey;
  }

  @Override
  public String toString() {
    return listenKey;
  }
}
