package com.binance.api.client.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Common static StringUtils used throughout Binance's API.
 */
public class StringUtils {

    /**
     * Convert a JSON string to pretty print JSON String
     *
     * @param jsonString a string already in a proper JSON syntax
     *
     * @return a pretty print version of the JSON String. If the
     *         provided String does not contain proper JSON syntax, then
     *         the original String is returned.
     *         {
     *             "symbol": "ETHUSDT",
     *             "orderId": 12345678,
     *             "clientOrderId": "smoD6joHgLUGlowNcZQcSf",
     *             "transactTime": 1511234502823
     *         }
     */
    public static String toPrettyJsonFormat( String jsonString ) {
        String prettyJson = jsonString;
        if(jsonString != null) {
            try {
                JsonParser parser = new JsonParser();
                JsonElement jsonElement = parser.parse(jsonString);
                if (jsonElement != null && jsonElement.isJsonObject()) {
                    JsonObject json = jsonElement.getAsJsonObject();
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    prettyJson = gson.toJson(json);
                }
            } catch (JsonParseException ex) {
                String message = new StringBuilder("Invalid JSON syntax. Cannot use toPrettyJsonFormat with with string [").append(prettyJson).append("]").toString();
                Logger.getLogger(StringUtils.class.getName()).log(Level.WARNING, message);
            }
        }
        return String.valueOf(prettyJson);
    }

    /**
     * Convert a JSON string to pretty print JSON String
     * This assumes you expect the provided object to output a JSON
     * string when the toString method is called.
     *
     * @param jsonString a string already in a proper JSON syntax
     *
     * @return a pretty print version of the JSON String. If the
     *         provided String does not contain proper JSON syntax, then
     *         the original String is returned.
     *         {
     *             "symbol": "ETHUSDT",
     *             "orderId": 12345678,
     *             "clientOrderId": "smoD6joHgLUGlowNcZQcSf",
     *             "transactTime": 1511234502823
     *         }
     */
    public static String toPrettyJsonFormat( Object jsonString ) {
        return toPrettyJsonFormat(String.valueOf(jsonString));
    }

    /**
     * Convert a JSON string to pretty print version and prints to console.
     * This assumes you expect the provided object to output a JSON
     * string when the toString method is called. If the resulting toString
     * call contains a non JSON syntax string then this will just print the
     * original result.
     *
     * @param jsonString a string already in a proper JSON syntax
     * results in a pretty print version of the JSON String. If the
     *         provided String does not contain proper JSON syntax, then
     *         the original String is returned.
     *         {
     *             "symbol": "ETHUSDT",
     *             "orderId": 12345678,
     *             "clientOrderId": "smoD6joHgLUGlowNcZQcSf",
     *             "transactTime": 1511234502823
     *         }
     */
    public static void printPrettyJsonFormat( Object jsonString ) {
        System.out.println(toPrettyJsonFormat(jsonString));
    }
}
