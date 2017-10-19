package com.binance.api.examples;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiClientFactory;

/**
 * Examples on how to use the general endpoints.
 */
public class GeneralEndpointsExampleAsync {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
    BinanceApiAsyncRestClient client = factory.newAsyncRestClient();

    // Test connectivity
    client.ping(response -> System.out.println("Ping succeeded."));

    // Check server time
    client.getServerTime(response -> System.out.println(response.getServerTime()));
  }
}
