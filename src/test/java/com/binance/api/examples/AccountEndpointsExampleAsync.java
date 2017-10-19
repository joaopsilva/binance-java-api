package com.binance.api.examples;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.domain.account.Account;

/**
 * Examples on how to get account information.
 */
public class AccountEndpointsExampleAsync {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_SECRET");
    BinanceApiAsyncRestClient client = factory.newAsyncRestClient();

    // Get account balances (async)
    client.getAccount((Account response) -> System.out.println(response.getAssetBalance("ETH")));

    // Get withdraw history (async)
    client.getWithdrawHistory("ETH", response -> System.out.println(response));

    // Get deposit history (async)
    client.getDepositHistory("ETH", response -> System.out.println(response));

    // Withdraw (async)
    client.withdraw("ETH", "0x123", "0.1", null, response -> {});
  }
}
