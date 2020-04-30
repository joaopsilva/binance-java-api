package com.binance.api.examples;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiMarginRestClient;
import com.binance.api.client.domain.TransferType;
import com.binance.api.client.domain.account.*;

import java.util.List;

/**
 * Examples on how to get margin account information.
 */
public class MarginAccountEndpointsLoanQueryExample {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_SECRET");
    BinanceApiMarginRestClient client = factory.newMarginRestClient();
    MarginTransaction borrowed = client.borrow("USDT", "310");
    System.out.println(borrowed.getTranId());
    LoanQueryResult queryResult = client.queryLoan("USDT", borrowed.getTranId());
    Loan loan = queryResult.getRows().get(0);
    System.out.println(loan.getStatus());
  }
}
