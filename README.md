# Java Binance API

binance-java-api is a lightweight Java library for interacting with the [Binance API](https://www.binance.com/restapipub.html), providing complete API coverage, and supporting synchronous and asynchronous requests, as well as event streaming using WebSockets.

**NOTE**: This project has won the [Binance API Competition](https://support.binance.com/hc/en-us/articles/115002294131-Java-Winner-of-Binance-API-Competition) for the Java language, and as such, this repository has been forked, and the official version can be found at https://github.com/binance-exchange/binance-java-api, where all further upgrades will be done.

## Features
* Support for synchronous and asynchronous REST requests to all [General](https://www.binance.com/restapipub.html#user-content-general-endpoints), [Market Data](https://www.binance.com/restapipub.html#user-content-market-data-endpoints), [Account](https://www.binance.com/restapipub.html#user-content-account-endpoints) endpoints, and [User](https://www.binance.com/restapipub.html#user-content-user-data-stream-endpoints) stream endpoints.
* Support for User Data, Trade, Kline, and Depth event streaming using [Binance WebSocket API](https://www.binance.com/restapipub.html#wss-endpoint).

## Installation
1. Install library into your Maven's local repository by running `mvn install`
2. Add the following Maven dependency to your project's `pom.xml`:
```
<dependency>
  <groupId>com.binance.api</groupId>
  <artifactId>binance-api-client</artifactId>
  <version>1.0.0</version>
</dependency>
```

Alternatively, you can clone this repository and run the [examples](https://github.com/joaopsilva/binance-java-api/tree/master/src/test/java/com/binance/api/examples).

## Examples

### Getting Started

There are three main client classes that can be used to interact with the API:

1. [`BinanceApiRestClient`](https://github.com/joaopsilva/binance-java-api/blob/master/src/main/java/com/binance/api/client/BinanceApiRestClient.java), a synchronous/blocking [Binance API](https://www.binance.com/restapipub.html) client;
2. [`BinanceApiAsyncRestClient`](https://github.com/joaopsilva/binance-java-api/blob/master/src/main/java/com/binance/api/client/BinanceApiAsyncRestClient.java), an asynchronous/non-blocking [Binance API](https://www.binance.com/restapipub.html) client;
3. [`BinanceApiWebSocketClient`](https://github.com/joaopsilva/binance-java-api/blob/master/src/main/java/com/binance/api/client/BinanceApiWebSocketClient.java), a data streaming client using [Binance WebSocket API](https://www.binance.com/restapipub.html#wss-endpoint).

These can be instantiated through the corresponding factory method of [`BinanceApiClientFactory`](https://github.com/joaopsilva/binance-java-api/blob/master/src/main/java/com/binance/api/client/BinanceApiClientFactory.java), by passing the [security parameters](https://www.binance.com/restapipub.html#user-content-endpoint-security-type) `API-KEY` and `SECRET`, which can be created at [https://www.binance.com/userCenter/createApi.html](https://www.binance.com/userCenter/createApi.html).

```java
BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("API-KEY", "SECRET");
BinanceApiRestClient client = factory.newRestClient();
```

If the client only needs to access endpoints which do not require additional security, then these parameters are optional.

Once the client is instantiated, it is possible to start making requests to the API.

### General endpoints

#### Test connectivity
```java
client.ping();
```
#### Check server time
```java
long serverTime = client.getServerTime();
System.out.println(serverTime);
```
<details>
 <summary>View Response</summary>
 
```java
1508380346873
```
</details>

### Market Data endpoints

#### Order book of a symbol
```java
OrderBook orderBook = client.getOrderBook("NEOETH", 10);
List<OrderBookEntry> asks = orderBook.getAsks();
OrderBookEntry firstAskEntry = asks.get(0);
System.out.println(firstAskEntry.getPrice() + " / " + firstAskEntry.getQty());
```
<details>
 <summary>View Response</summary>
 
 ```java
0.09200000 / 5.52000000
```
</details>

#### Compressed/Aggregate trades list of a symbol
```java
List<AggTrade> aggTrades = client.getAggTrades("NEOETH");
System.out.println(aggTrades);
```
<details>
 <summary>View Response</summary>
 
 ```java
[AggTrade[aggregatedTradeId=30593,price=0.09880800,quantity=40.89000000,firstBreakdownTradeId=33363,lastBreakdownTradeId=33363,tradeTime=1508331041246,isBuyerMaker=true], ...]
```
</details>

#### Weekly candlestick bars for a symbol
```java
List<Candlestick> candlesticks = client.getCandlestickBars("NEOETH", CandlestickInterval.WEEKLY);
System.out.println(candlesticks);
```
<details>
 <summary>View Response</summary>
 
 ```java
[Candlestick[openTime=1506297600000,open=0.09700000,high=0.12000100,low=0.05500000,close=0.11986900,volume=25709.37000000,closeTime=1506902399999,quoteAssetVolume=2649.80091051,numberOfTrades=2435,takerBuyBaseAssetVolume=10520.59000000,takerBuyQuoteAssetVolume=1101.94985388], ...] 
```
</details>

#### Latest price of a symbol
```java
TickerStatistics tickerStatistics = client.get24HrPriceStatistics("NEOETH");
System.out.println(tickerStatistics.getLastPrice());
```
<details>
 <summary>View Response</summary>
 
 ```java
0.09100100
```
</details>

#### Getting all latests prices
```java
List<TickerPrice> allPrices = client.getAllPrices();
System.out.println(allPrices);
```
<details>
 <summary>View Response</summary>
 
 ```java
[TickerPrice[symbol=ETHBTC,price=0.05590400], TickerPrice[symbol=LTCBTC,price=0.01073300], ...]
```
</details>

### Account Data endpoints

#### Get account balances
```java
Account account = client.getAccount();
System.out.println(account.getBalances());
System.out.println(account.getAssetBalance("ETH").getFree());
```
<details>
 <summary>View Response</summary>
 
 ```java
AssetBalance[asset=ETH,free=0.10000000,locked=0.00000000]
0.10000000
```
</details>

#### Get list of trades for an account and a symbol
```java
List<Trade> myTrades = client.getMyTrades("NEOETH");
System.out.println(myTrades);
```
<details>
 <summary>View Response</summary>
 
 ```java
[Trade[id=123,price=0.00000100,qty=1000.00000000,commission=0.00172100,commissionAsset=ETH,time=1507927870561,buyer=false,maker=false,bestMatch=true,symbol=<null>,orderId=11289], Trade[id=123,price=0.00001000,qty=3.00000000,commission=0.00000003,commissionAsset=ETH,time=1507927874215,buyer=false,maker=false,bestMatch=true,symbol=<null>,orderId=123]]
```
</details>

#### Get account open orders for a symbol
```java
List<Order> openOrders = client.getOpenOrders(new OrderRequest("LINKETH"));
System.out.println(openOrders);
```
<details>
 <summary>View Response</summary>
 
 ```java
[Order[symbol=LINKETH,orderId=12345,clientOrderId=XYZ,price=0.00010000,origQty=1000.00000000,executedQty=0.00000000,status=NEW,timeInForce=GTC,type=LIMIT,side=BUY,stopPrice=0.00000000,icebergQty=0.00000000,time=1508382291552]]
```
</details>

#### Get order status
```java
Order order = client.getOrderStatus(new OrderStatusRequest("LINKETH", 12345L));
System.out.println(order.getExecutedQty());
```
<details>
 <summary>View Response</summary>
 
 ```java
0.00000000
```
</details>

#### Placing a MARKET order
```java
NewOrderResponse newOrderResponse = client.newOrder(marketBuy("LINKETH", "1000").orderRespType(OrderResponseType.FULL));
List<Trade> fills = newOrderResponse.getFills();
System.out.println(newOrderResponse.getClientOrderId());
```
<details>
 <summary>View Response</summary>
 
 ```java
XXXXXfc2XXzTXXGs66ZcXX
```
</details>

#### Placing a LIMIT order
```java
NewOrderResponse newOrderResponse = client.newOrder(limitBuy("LINKETH", TimeInForce.GTC, "1000", "0.0001"));
System.out.println(newOrderResponse.getTransactTime());
```
<details>
 <summary>View Response</summary>
 
 ```java
1508382322725
```
</details>

#### Canceling an order
```java
client.cancelOrder(new CancelOrderRequest("LINKETH", 123015L));
```

#### Withdraw

In order to be able to withdraw programatically, please enable the `Enable Withdrawals` option in the API settings.

```java
client.withdraw("ETH", "0x123", "0.1", null);
```

#### Fetch withdraw history

```java
WithdrawHistory withdrawHistory = client.getWithdrawHistory("ETH");
System.out.println(withdrawHistory);
```
<details>
 <summary>View Response</summary>
 
 ```java
WithdrawHistory[withdrawList=[Withdraw[amount=0.1,address=0x123,asset=ETH,applyTime=2017-10-13 20:59:38,successTime=2017-10-13 21:20:09,txId=0x456,id=789]],success=true]
```
</details>

#### Fetch deposit history
```java
DepositHistory depositHistory = client.getDepositHistory("ETH");
System.out.println(depositHistory);
```
<details>
 <summary>View Response</summary>
 
 ```java
DepositHistory[depositList=[Deposit[amount=0.100000000000000000,asset=ETH,insertTime=2017-10-18 13:03:39], Deposit[amount=1.000000000000000000,asset=NEO,insertTime=2017-10-13 20:24:04]],success=true]
```
</details>

#### Get deposit address
```java
DepositAddress depositAddress = client.getDepositAddress("ETH");
System.out.println(depositAddress);
```
<details>
 <summary>View Response</summary>
 
 ```java
DepositAddress[address=0x99...,success=true,addressTag=,asset=ETH]
```
</details>

### User stream endpoints

#### Start user data stream, keepalive, and close data stream
```java
String listenKey = client.startUserDataStream();
client.keepAliveUserDataStream(listenKey);
client.closeUserDataStream(listenKey);
```

### WebSocket API

#### Initialize the WebSocket client
```java
BinanceApiWebSocketClient client = BinanceApiClientFactory.newInstance().newWebSocketClient();
```

User needs to be aware that REST symbols which are `upper case` differ from WebSocket symbols which must be `lower case`.
In scenario of subscription with upper case styled symbol, server will return no error and subscribe to given channel - however, no events will be pushed.   

#### Handling web socket errors

Each of the methods on `BinanceApiWebSocketClient`, which opens a new web socket, takes a `BinanceApiCallback`, which is
called for each event received from the Binance servers. 

The `BinanceApiCallback` interface also has a `onFailure(Throwable)` method, which, optionally, can be implemented to 
receive notifications if the web-socket fails, e.g. disconnection.   

```java
client.onAggTradeEvent(symbol.toLowerCase(), new BinanceApiCallback<AggTradeEvent>() {
    @Override
    public void onResponse(final AggTradeEvent response) {
        System.out.println(response);
    }

    @Override
    public void onFailure(final Throwable cause) {
        System.err.println("Web socket failed");
        cause.printStackTrace(System.err);
    }
});
```

#### Closing web sockets

Each of the methods on `BinanceApiWebSocketClient`, which opens a new web socket, also returns a `Closeable`.
This `Closeable` can be used to close the underlying web socket and free any associated resources, e.g.

```java
Closable ws = client.onAggTradeEvent("ethbtc", someCallback);
// some time later...
ws.close();
```

#### Listen for aggregated trade events for ETH/BTC
```java
client.onAggTradeEvent("ethbtc", (AggTradeEvent response) -> {
  System.out.println(response.getPrice());
  System.out.println(response.getQuantity());
});
```
<details>
 <summary>View Response</summary>
 
 ```java
0.05583500 / 1.06400000
1508383333069
0.05557200 / 2.00000000
1508383345070
0.05583200 / 2.68500000
1508383352961
...
```
</details>

#### Listen for changes in the order book for ETH/BTC
```java
client.onDepthEvent("ethbtc", (DepthEvent response) -> {
  System.out.println(response.getAsks());
});
```
<details>
 <summary>View Response</summary>
 
 ```java
[OrderBookEntry[price=0.05559500,qty=7.94200000], OrderBookEntry[price=0.05559800,qty=0.00000000]]
[OrderBookEntry[price=0.05558400,qty=30.61800000], OrderBookEntry[price=0.05559500,qty=0.00000000], OrderBookEntry[price=0.05560600,qty=8.32100000]]
[OrderBookEntry[price=0.05559100,qty=7.86600000], OrderBookEntry[price=0.05560600,qty=0.00000000], OrderBookEntry[price=0.05607700,qty=5.15500000], OrderBookEntry[price=0.05620700,qty=0.00000000], OrderBookEntry[price=0.05842200,qty=0.00000000]]
[OrderBookEntry[price=0.05558400,qty=29.61700000]]
...
```
</details>

#### Get 1m candlesticks in real-time for ETH/BTC
```java
client.onCandlestickEvent("ethbtc", CandlestickInterval.ONE_MINUTE, response -> System.out.println(response));
```
<details>
 <summary>View Response</summary>
 
 ```java
CandlestickEvent[eventType=kline,eventTime=1508417055113,symbol=ETHBTC,openTime=1508417040000,open=0.05376300,high=0.05376300,low=0.05372900,close=0.05372900,volume=0.49400000,closeTime=1508417099999,intervalId=1m,firstTradeId=2199019,lastTradeId=2199020,quoteAssetVolume=0.02654552,numberOfTrades=2,takerBuyBaseAssetVolume=0.00000000,takerBuyQuoteAssetVolume=0.00000000,isBarFinal=false]
CandlestickEvent[eventType=kline,eventTime=1508417055145,symbol=ETHBTC,openTime=1508417040000,open=0.05376300,high=0.05376300,low=0.05371700,close=0.05371700,volume=0.62900000,closeTime=1508417099999,intervalId=1m,firstTradeId=2199019,lastTradeId=2199021,quoteAssetVolume=0.03379731,numberOfTrades=3,takerBuyBaseAssetVolume=0.00000000,takerBuyQuoteAssetVolume=0.00000000,isBarFinal=false]
CandlestickEvent[eventType=kline,eventTime=1508417096085,symbol=ETHBTC,openTime=1508417040000,open=0.05376300,high=0.05376300,low=0.05370900,close=0.05370900,volume=0.68000000,closeTime=1508417099999,intervalId=1m,firstTradeId=2199019,lastTradeId=2199022,quoteAssetVolume=0.03653646,numberOfTrades=4,takerBuyBaseAssetVolume=0.00000000,takerBuyQuoteAssetVolume=0.00000000,isBarFinal=false]
...
```
</details>

#### Keep a local depth cache for a symbol

Please see [DepthCacheExample.java](https://github.com/joaopsilva/binance-java-api/blob/master/src/test/java/com/binance/api/examples/DepthCacheExample.java) for an implementation which uses the binance-java-api for maintaining a local depth cache for a symbol. In the same folder, you can also find how to do caching of account balances, aggregated trades, and klines/candlesticks.

<details>
 <summary>View Response</summary>
 
 ```java
ASKS:
0.05690700 / 6.15100000
0.05447800 / 0.09500000
0.05447700 / 28.22000000
0.05439000 / 0.54500000
0.05438400 / 1.10300000
0.05436600 / 0.06100000
0.05434000 / 0.05500000
0.05432800 / 3.45100000
0.05422700 / 1.11100000
0.05410600 / 5.85900000
0.05409300 / 4.50000000
BIDS:
0.05390000 / 2.26000000
0.05389000 / 15.00000000
0.05385600 / 1.95000000
0.05367900 / 0.10000000
0.05366700 / 2.27600000
0.05360000 / 10.96100000
0.05348500 / 14.04000000
0.05345000 / 0.56100000
0.05336200 / 21.10000000
0.05336100 / 21.15000000
0.05306600 / 0.21100000
0.05116300 / 10.95000000
BEST ASK: 0.05409300 / 4.50000000
BEST BID: 0.05390000 / 2.26000000
...
```
</details>

#### Listen for changes in the account

```java
client.onUserDataUpdateEvent(listenKey, response -> {
  if (response.getEventType() == UserDataUpdateEventType.ACCOUNT_UPDATE) {
    AccountUpdateEvent accountUpdateEvent = response.getAccountUpdateEvent();
    
    // Print new balances of every available asset
    System.out.println(accountUpdateEvent.getBalances());
  } else {
    OrderTradeUpdateEvent orderTradeUpdateEvent = response.getOrderTradeUpdateEvent();
    
    // Print details about an order/trade
    System.out.println(orderTradeUpdateEvent);

    // Print original quantity
    System.out.println(orderTradeUpdateEvent.getOriginalQuantity());

    // Or price
    System.out.println(orderTradeUpdateEvent.getPrice());
  }
});
```

#### Multi-channel subscription
Client provides a way for user to subscribe to multiple channels using same websocket - to achieve that user needs to coma-separate symbols as it is in following examples.

````java
client.onAggTradeEvent("ethbtc,ethusdt", (AggTradeEvent response) -> {
  if (Objects.equals(response.getSymbol(),"ethbtc")) {
      // handle ethbtc event
  } else if(Objects.equals(response.getSymbol()),"ethusdt")) {
      // handle ethusdt event
  }
});
````
````java
client.onDepthEvent("ethbtc,ethusdt", (DepthEvent response) -> {
  if (Objects.equals(response.getSymbol(),"ethbtc")) {
      // handle ethbtc event
  } else if(Objects.equals(response.getSymbol()),"ethusdt")) {
      // handle ethusdt event
  }
});
````
````java
client.onCandlestickEvent("ethbtc,ethusdt", CandlestickInterval.ONE_MINUTE, (CandlestickEvent response) -> {
  if (Objects.equals(response.getSymbol(),"ethbtc")) {
      // handle ethbtc event
  } else if(Objects.equals(response.getSymbol()),"ethusdt")) {
      // handle ethusdt event
  }
});
````

### Asynchronous requests

To make an asynchronous request it is necessary to use the `BinanceApiAsyncRestClient`, and call the method with the same name as in the synchronous version, but passing a callback [`BinanceApiCallback`](https://github.com/joaopsilva/binance-java-api/blob/master/src/main/java/com/binance/api/client/BinanceApiCallback.java) that handles the response whenever it arrives.

#### Get latest price of a symbol asynchronously
```java
client.get24HrPriceStatistics("NEOETH", (TickerStatistics response) -> {
  System.out.println(response.getLastPrice());
  System.out.println(response.getVolume());
});
```
<details>
 <summary>View Response</summary>
 
 ```java
0.09100100
```
</details>

#### Placing a LIMIT order asynchronously
```java
client.newOrder(limitBuy("LINKETH", TimeInForce.GTC, "1000", "0.0001"), (NewOrderResponse response) -> {
  System.out.println(response.getTransactTime());
});
```
<details>
 <summary>View Response</summary>
 
 ```java
1508382322725
```
</details>

### Exception handling

Every API method can potentially throw an unchecked `BinanceApiException` which wraps the error message returned from the Binance API, or an exception, in case the request never properly reached the server.

```java
try {
  client.getOrderBook("UNKNOWN", 10);
} catch (BinanceApiException e) {
  System.out.println(e.getError().getCode()); // -1121
  System.out.println(e.getError().getMsg());  // Invalid symbol
}
```
<details>
 <summary>View Response</summary>
 
```java
-1121
Invalid symbol
```
</details>

### More examples
An extensive set of examples, covering most aspects of the API, can be found at https://github.com/joaopsilva/binance-java-api/tree/master/src/test/java/com/binance/api/examples.
