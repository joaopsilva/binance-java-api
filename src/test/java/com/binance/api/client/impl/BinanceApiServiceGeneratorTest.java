package com.binance.api.client.impl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author andy coates
 * created 08/02/2018.
 */
public class BinanceApiServiceGeneratorTest {
    @Test
    public void shouldOnlyAddAuthInterceptorOnce() throws Exception {
        // Given:
        BinanceApiServiceGenerator.createService(BinanceApiService.class, "someKey", "someValue");
        final int initialSize = BinanceApiServiceGenerator.httpClient.interceptors().size();

        // When:
        BinanceApiServiceGenerator.createService(BinanceApiService.class, "someKey", "someValue");

        // Then
        assertEquals(BinanceApiServiceGenerator.httpClient.interceptors().size(), initialSize);
    }
}