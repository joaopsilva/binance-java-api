package com.binance.api.domain.general;

import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.domain.general.FilterType;
import com.binance.api.client.domain.general.RateLimit;
import com.binance.api.client.domain.general.RateLimitInterval;
import com.binance.api.client.domain.general.RateLimitType;
import com.binance.api.client.domain.general.SymbolFilter;
import com.binance.api.client.domain.general.SymbolInfo;
import com.binance.api.client.domain.general.SymbolStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Test deserialization of exchange information.
 */
public class ExchangeInfoDeserializerTest {

  @Test
  public void testExchangeInfoDeserialization() {
    final String json = "{\n" +
        "  \"timezone\": \"UTC\",\n" +
        "  \"serverTime\": 1508631584636,\n" +
        "  \"rateLimits\": [{\n" +
        "      \"rateLimitType\": \"REQUEST_WEIGHT\",\n" +
        "      \"interval\": \"MINUTE\",\n" +
        "      \"limit\": 1200\n" +
        "    },\n" +
        "    {\n" +
        "      \"rateLimitType\": \"ORDERS\",\n" +
        "      \"interval\": \"SECOND\",\n" +
        "      \"limit\": 10\n" +
        "    },\n" +
        "    {\n" +
        "      \"rateLimitType\": \"ORDERS\",\n" +
        "      \"interval\": \"DAY\",\n" +
        "      \"limit\": 100000\n" +
        "    }\n" +
        "  ],\n" +
        "  \"exchangeFilters\": [],\n" +
        "  \"symbols\": [{\n" +
        "    \"symbol\": \"ETHBTC\",\n" +
        "    \"status\": \"TRADING\",\n" +
        "    \"baseAsset\": \"ETH\",\n" +
        "    \"baseAssetPrecision\": 8,\n" +
        "    \"quoteAsset\": \"BTC\",\n" +
        "    \"quotePrecision\": 8,\n" +
        "    \"orderTypes\": [\"LIMIT\", \"MARKET\"],\n" +
        "    \"icebergAllowed\": false,\n" +
        "    \"filters\": [{\n" +
        "      \"filterType\": \"PRICE_FILTER\",\n" +
        "      \"minPrice\": \"0.00000100\",\n" +
        "      \"maxPrice\": \"100000.00000000\",\n" +
        "      \"tickSize\": \"0.00000100\"\n" +
        "    }, {\n" +
        "      \"filterType\": \"LOT_SIZE\",\n" +
        "      \"minQty\": \"0.00100000\",\n" +
        "      \"maxQty\": \"100000.00000000\",\n" +
        "      \"stepSize\": \"0.00100000\"\n" +
        "    }, {\n" +
        "      \"filterType\": \"MIN_NOTIONAL\",\n" +
        "      \"minNotional\": \"0.00100000\"\n" +
        "    }]\n" +
        "  }]" +
        "}";
    ObjectMapper mapper = new ObjectMapper();
    try {
      ExchangeInfo exchangeInfo = mapper.readValue(json, ExchangeInfo.class);
      System.out.println(exchangeInfo);
      assertEquals(exchangeInfo.getTimezone(), "UTC");
      assertEquals((long)exchangeInfo.getServerTime(), 1508631584636L);

      List<RateLimit> rateLimits = exchangeInfo.getRateLimits();
      assertEquals(rateLimits.size(), 3);
      testRateLimit(rateLimits.get(0), RateLimitType.REQUEST_WEIGHT, RateLimitInterval.MINUTE, 1200);
      testRateLimit(rateLimits.get(1), RateLimitType.ORDERS, RateLimitInterval.SECOND, 10);
      testRateLimit(rateLimits.get(2), RateLimitType.ORDERS, RateLimitInterval.DAY, 100000);

      List<SymbolInfo> symbols = exchangeInfo.getSymbols();
      assertEquals(symbols.size(), 1);
      SymbolInfo symbolInfo = symbols.get(0);
      assertEquals(symbolInfo.getSymbol(), "ETHBTC");
      assertEquals(symbolInfo.getStatus(), SymbolStatus.TRADING);
      assertEquals(symbolInfo.getBaseAsset(), "ETH");
      assertEquals((int)symbolInfo.getBaseAssetPrecision(), 8);
      assertEquals(symbolInfo.getQuoteAsset(), "BTC");
      assertEquals((int)symbolInfo.getQuotePrecision(), 8);
      assertEquals(symbolInfo.getOrderTypes(), Arrays.asList(OrderType.LIMIT, OrderType.MARKET));
      assertFalse(symbolInfo.isIcebergAllowed());

      List<SymbolFilter> symbolFilters = symbolInfo.getFilters();
      assertEquals(symbolFilters.size(), 3);

      SymbolFilter priceFilter = symbolFilters.get(0);
      assertEquals(priceFilter.getFilterType(), FilterType.PRICE_FILTER);
      assertEquals(priceFilter.getMinPrice(), "0.00000100");
      assertEquals(priceFilter.getMaxPrice(), "100000.00000000");
      assertEquals(priceFilter.getTickSize(), "0.00000100");

      SymbolFilter lotSizeFilter = symbolFilters.get(1);
      assertEquals(lotSizeFilter.getFilterType(), FilterType.LOT_SIZE);
      assertEquals(lotSizeFilter.getMinQty(), "0.00100000");
      assertEquals(lotSizeFilter.getMaxQty(), "100000.00000000");
      assertEquals(lotSizeFilter.getStepSize(), "0.00100000");

      SymbolFilter minNotionalFilter = symbolFilters.get(2);
      assertEquals(minNotionalFilter.getFilterType(), FilterType.MIN_NOTIONAL);
      assertEquals(minNotionalFilter.getMinNotional(), "0.00100000");
    } catch (IOException e) {
      fail();
    }
  }

  private void testRateLimit(RateLimit rateLimit, RateLimitType expectedRateLimitType, RateLimitInterval expectedInterval, int expectedLimit) {
    assertEquals(rateLimit.getRateLimitType(), expectedRateLimitType);
    assertEquals(rateLimit.getInterval(), expectedInterval);
    assertEquals((long)rateLimit.getLimit(), expectedLimit);
  }
}
