package com.binance.api.examples;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.constant.Util;
import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.AssetBalance;

/**
 * Example how to get total of balances on your account
 */
public class TotalAccountBalanceExample {


    public static void main(String[] args) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_SECRET");
        BinanceApiRestClient client = factory.newRestClient();

        // Get account balances
        Account account = client.getAccount(Long.valueOf(60_000L), Long.valueOf(System.currentTimeMillis()));

        // Get total account balance in BTC (spot only)
        TotalAccountBalanceExample accountBalance = new TotalAccountBalanceExample();
        double totalBalanceInBTC = accountBalance.getTotalAccountBalance(client,account);
        System.out.println(totalBalanceInBTC);
        // Get total account balance in USDT (spot only)
        double totalBalanceInUSDT = totalBalanceInBTC * Double.parseDouble(client.getPrice("BTCUSDT").getPrice());
        System.out.println(totalBalanceInUSDT);




    }

    // Get total account balance in BTC (spot only)
    public double getTotalAccountBalance(BinanceApiRestClient client, Account account) {
        double totalBalance = 0;
        for (AssetBalance balance : account.getBalances()) {
            double free = Double.parseDouble(balance.getFree());
            double locked = Double.parseDouble(balance.getLocked());
            String ticker = balance.getAsset() + Util.BTC_TICKER;
            String tickerReverse = Util.BTC_TICKER + balance.getAsset();
            if (free + locked != 0) {
                if (Util.isFiatCurrency(balance.getAsset())) {
                    double price = Double.parseDouble(client.getPrice(tickerReverse).getPrice());
                    double amount = (free + locked) / price;
                    totalBalance += amount;
                } else {
                    double price = Double.parseDouble(client.getPrice(ticker).getPrice());
                    double amount = price * (free + locked);
                    totalBalance += amount;
                }

            }
        }

        return totalBalance;

    }



}
