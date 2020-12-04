package com.binance.api.domain.market;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import com.binance.api.client.domain.market.Candlestick;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests the adequate deserialization of candlestick JSON information.
 */
public class CandlestickDeserializerTest {

  @Test
  public void testCandlestickDeserializerTest() {
    final String candlestickJson = "[\n" +
        "    1499040000000,\n" +
        "        \"0.01634790\",\n" +
        "        \"0.80000000\",\n" +
        "        \"0.01575800\",\n" +
        "        \"0.01577100\",\n" +
        "        \"148976.11427815\",\n" +
        "        1499644799999,\n" +
        "        \"2434.19055334\",\n" +
        "        308,\n" +
        "        \"1756.87402397\",\n" +
        "        \"28.46694368\",\n" +
        "        \"17928899.62484339\"\n" +
        "        ]";
    ObjectMapper mapper = new ObjectMapper();
    try {
      Candlestick candlestick = mapper.readValue(candlestickJson, Candlestick.class);
      assertEquals(candlestick.getOpenTime().longValue(), 1499040000000L);
      assertEquals(candlestick.getOpen(), "0.01634790");
      assertEquals(candlestick.getHigh(), "0.80000000");
      assertEquals(candlestick.getLow(), "0.01575800");
      assertEquals(candlestick.getClose(), "0.01577100");
      assertEquals(candlestick.getVolume(), "148976.11427815");
      assertEquals(candlestick.getCloseTime().longValue(), 1499644799999L);
      assertEquals(candlestick.getQuoteAssetVolume(), "2434.19055334");
      assertEquals(candlestick.getNumberOfTrades().longValue(), 308L);
      assertEquals(candlestick.getTakerBuyBaseAssetVolume(), "1756.87402397");
      assertEquals(candlestick.getTakerBuyQuoteAssetVolume(), "28.46694368");
    } catch (IOException e) {
      fail();
    }
  }
}
