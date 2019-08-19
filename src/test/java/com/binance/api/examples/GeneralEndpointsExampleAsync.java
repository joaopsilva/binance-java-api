package com.binance.api.examples;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.domain.general.Asset;
import com.binance.api.client.domain.general.FilterType;
import com.binance.api.client.domain.general.SymbolFilter;
import com.binance.api.client.domain.general.SymbolInfo;

import java.util.List;

/**
 * Examples on how to use the general endpoints.
 */
public class GeneralEndpointsExampleAsync {

  public static void main(String[] args) throws InterruptedException {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
    BinanceApiAsyncRestClient client = factory.newAsyncRestClient();

    // Test connectivity
    client.ping(response -> System.out.println("Ping succeeded."));

    // Check server time
    client.getServerTime(response -> System.out.println(response.getServerTime()));

    // Exchange info
    client.getExchangeInfo(exchangeInfo -> {
      System.out.println(exchangeInfo.getTimezone());
      System.out.println(exchangeInfo.getSymbols());

      // Obtain symbol information
      SymbolInfo symbolInfo = exchangeInfo.getSymbolInfo("ETHBTC");
      System.out.println(symbolInfo.getStatus());

      SymbolFilter priceFilter = symbolInfo.getSymbolFilter(FilterType.PRICE_FILTER);
      System.out.println(priceFilter.getMinPrice());
      System.out.println(priceFilter.getTickSize());
    });

    // Obtain asset information
    client.getAllAssets(allAssets ->
        System.out.println(allAssets.stream().filter(asset -> asset.getAssetCode().equals("BNB")).findFirst().get()));
  }
}
