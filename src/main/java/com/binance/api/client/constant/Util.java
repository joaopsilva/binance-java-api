package com.binance.api.client.constant;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Util {

    public static final List<String> FIAT_CURRENCY = Collections.unmodifiableList(Arrays.asList("USDT", "BUSD", "PAX", "TUSD", "USDC", "NGN", "RUB", "USDS"));

    public static final String BTC_TICKER = "BTC";

    private Util() {
        throw new java.lang.UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static boolean isFiatCurrency(String symbol) {
        for (String fiat : FIAT_CURRENCY) {
            if (symbol.equals(fiat)) return true;
        }
        return false;
    }
}
