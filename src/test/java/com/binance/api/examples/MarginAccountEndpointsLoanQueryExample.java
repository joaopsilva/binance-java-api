package com.binance.api.examples;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiMarginRestClient;
import com.binance.api.client.domain.account.MarginTransaction;
import com.binance.api.client.domain.account.RepayQueryResult;

/**
 * Examples on how to get margin account information.
 */
public class MarginAccountEndpointsLoanQueryExample {

    public static void main(String[] args) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_SECRET");
        BinanceApiMarginRestClient client = factory.newMarginRestClient();
        MarginTransaction borrowed = client.borrow("USDT", "310");
        System.out.println(borrowed.getTranId());
        MarginTransaction repaid = client.repay("USDT", "310");
        System.out.println(repaid);
        RepayQueryResult repayQueryResult = client.queryRepay("BNB", System.currentTimeMillis() - 1000);
        System.out.println(repayQueryResult);
    }
}
