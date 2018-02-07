package com.binance.api.client.utils;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.market.Candlestick;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilsTest {
    
    private static String candlestickJson;
    private static Candlestick candlestick;
    private static ToStringStyle DEFAULT_TO_STRING_BUILDER_STYLE;
    
    public StringUtilsTest() {  
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        DEFAULT_TO_STRING_BUILDER_STYLE = BinanceApiConstants.TO_STRING_BUILDER_STYLE;
        
        candlestickJson = "[\n"
                + "    1499040000000,\n"
                + "        \"0.01634790\",\n"
                + "        \"0.80000000\",\n"
                + "        \"0.01575800\",\n"
                + "        \"0.01577100\",\n"
                + "        \"148976.11427815\",\n"
                + "        1499644799999,\n"
                + "        \"2434.19055334\",\n"
                + "        308,\n"
                + "        \"1756.87402397\",\n"
                + "        \"28.46694368\",\n"
                + "        \"17928899.62484339\"\n"
                + "        ]";
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            candlestick = mapper.readValue(candlestickJson, Candlestick.class);
        } catch (IOException e) {
            fail();
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        BinanceApiConstants.TO_STRING_BUILDER_STYLE = DEFAULT_TO_STRING_BUILDER_STYLE;
    }

    /**
     * Test of toPrettyJsonFormat methods, of class StringUtils.
     */
    @Test
    public void testToPrettyJsonFormat_String() {
        // candlestickJson is not a proper json syntax, this test shows that a malformed string can be  
        // given to toPrettyPrintJsonFormat and it will just return the string value of the provided object
        String expResult = candlestickJson;
        String result = StringUtils.toPrettyJsonFormat(candlestickJson);
        assertEquals(expResult, result);
        
        // shows that a no error will happen if the provided object is null
        assertEquals("null", StringUtils.toPrettyJsonFormat(null));
        
        //show that the toString result a domain object with Default styling cannot be serialized into the same domain object
        Logger.getLogger(StringUtilsTest.class.getName()).log(Level.INFO, "The Following Warning is expected and can be ignored");
        String prettyJsonFormat = StringUtils.toPrettyJsonFormat(candlestick);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.readValue(prettyJsonFormat, Candlestick.class);
            fail();
        } catch (JsonParseException ex) {
            assertEquals("JsonParseException", ex.getClass().getSimpleName());
        } catch (IOException ex) {
            Logger.getLogger(StringUtilsTest.class.getName()).log(Level.WARNING, "Will not see this error here");
        }
        
        //show that the toString result of a domain object with JSON styling can be serialized into the same domain object again
        BinanceApiConstants.TO_STRING_BUILDER_STYLE = ToStringStyle.JSON_STYLE;
        prettyJsonFormat = StringUtils.toPrettyJsonFormat(candlestick);

        JsonParser parser = new JsonParser();
        JsonElement tradeElement = parser.parse(prettyJsonFormat);
        JsonObject cand = tradeElement.getAsJsonObject();

        Candlestick candlestick1 = new Candlestick();
        candlestick1.setOpenTime(cand.get("openTime").getAsLong());
        candlestick1.setOpen(cand.get("open").getAsString());
        candlestick1.setHigh(cand.get("high").getAsString());
        candlestick1.setLow(cand.get("low").getAsString());
        candlestick1.setClose(cand.get("close").getAsString());
        candlestick1.setVolume(cand.get("volume").getAsString());
        candlestick1.setCloseTime(cand.get("closeTime").getAsLong());
        candlestick1.setQuoteAssetVolume(cand.get("quoteAssetVolume").getAsString());
        candlestick1.setNumberOfTrades(cand.get("numberOfTrades").getAsLong());
        candlestick1.setTakerBuyBaseAssetVolume(cand.get("takerBuyBaseAssetVolume").getAsString());
        candlestick1.setTakerBuyQuoteAssetVolume(cand.get("takerBuyQuoteAssetVolume").getAsString());

        Logger.getLogger(StringUtilsTest.class.getName()).log(Level.INFO, "The Following is a Valid pretty printed JSON syntax");
        StringUtils.printPrettyJsonFormat(candlestick1);
        
        // Clean up
        BinanceApiConstants.TO_STRING_BUILDER_STYLE = DEFAULT_TO_STRING_BUILDER_STYLE;
        
        // Final test to show that the original candlestick tostring is equal to the newly created candlestick after being deseralized, ran though a formatter
        // reserialized and had its string styling changed before running a comparison on their equality. 
        assertEquals(candlestick.toString(), candlestick1.toString());
    }
}
