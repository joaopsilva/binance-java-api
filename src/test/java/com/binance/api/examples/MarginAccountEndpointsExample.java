package com.binance.api.examples;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiMarginRestClient;
import com.binance.api.client.domain.TransferType;
import com.binance.api.client.domain.account.MarginAccount;
import com.binance.api.client.domain.account.MarginTransaction;
import com.binance.api.client.domain.account.Trade;

import java.util.List;

/**
 * Examples on how to get margin account information.
 */
public class MarginAccountEndpointsExample {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_SECRET");
    BinanceApiMarginRestClient client = factory.newMarginRestClient();

    // Get account balances
    MarginAccount marginAccount = client.getAccount();
    System.out.println(marginAccount.getUserAssets());
    System.out.println(marginAccount.getAssetBalance("ETH"));
    System.out.println(marginAccount.getMarginLevel());

    // Get list of trades
    List<Trade> myTrades = client.getMyTrades("NEOETH");
    System.out.println(myTrades);

    // Transfer, borrow, repay
    MarginTransaction spotToMargin = client.transfer("USDT", "1", TransferType.SPOT_TO_MARGIN);
    System.out.println(spotToMargin.getTranId());
    MarginTransaction borrowed = client.borrow("USDT", "1");
    System.out.println(borrowed.getTranId());
    MarginTransaction repayed = client.repay("USDT", "1");
    System.out.println(repayed.getTranId());
    MarginTransaction marginToSpot = client.transfer("USDT", "1", TransferType.MARGIN_TO_SPOT);
    System.out.println(marginToSpot.getTranId());
  }
}
