package com.binance.api.examples;

import com.binance.api.client.BinanceApiAsyncMarginRestClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.request.CancelOrderRequest;
import com.binance.api.client.domain.account.request.OrderRequest;
import com.binance.api.client.domain.account.request.OrderStatusRequest;

import static com.binance.api.client.domain.account.MarginNewOrder.limitBuy;

/**
 * Examples on how to place orders, cancel them, and query account information.
 */
public class MarginOrdersExampleAsync {

    public static void main(String[] args) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_SECRET");
        BinanceApiAsyncMarginRestClient client = factory.newAsyncMarginRestClient();

        // Getting list of open orders
        client.getOpenOrders(new OrderRequest("LINKETH"), response -> System.out.println(response));

        // Get status of a particular order
        client.getOrderStatus(new OrderStatusRequest("LINKETH", 745262L),
                response -> System.out.println(response));

        // Canceling an order
        client.cancelOrder(new CancelOrderRequest("LINKETH", 756703L),
                response -> System.out.println(response));

        // Placing a real LIMIT order
        client.newOrder(limitBuy("LINKETH", TimeInForce.GTC, "1000", "0.0001"),
                response -> System.out.println(response));
    }
}
