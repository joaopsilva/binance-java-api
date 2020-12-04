package com.binance.api.client.domain.event;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Custom serializer for a candlestick stream event, since the structure of the candlestick json differ from the one in the REST API.
 *
 * @see CandlestickEvent
 */
public class CandlestickEventSerializer extends JsonSerializer<CandlestickEvent> {

  @Override
  public void serialize(CandlestickEvent candlestickEvent, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeStartObject();
    
    // Write header
    gen.writeStringField("e", candlestickEvent.getEventType());
    gen.writeNumberField("E", candlestickEvent.getEventTime());
    gen.writeStringField("s", candlestickEvent.getSymbol());
    
    // Write candlestick data
    gen.writeObjectFieldStart("k");
    gen.writeNumberField("t", candlestickEvent.getOpenTime().longValue());
    gen.writeNumberField("T", candlestickEvent.getCloseTime().longValue());
    gen.writeStringField("i", candlestickEvent.getIntervalId());
    gen.writeNumberField("f", candlestickEvent.getFirstTradeId().longValue());
    gen.writeNumberField("L", candlestickEvent.getLastTradeId().longValue());
    gen.writeStringField("o", candlestickEvent.getOpen());
    gen.writeStringField("c", candlestickEvent.getClose());
    gen.writeStringField("h", candlestickEvent.getHigh());
    gen.writeStringField("l", candlestickEvent.getLow());
    gen.writeStringField("v", candlestickEvent.getVolume());
    gen.writeNumberField("n", candlestickEvent.getNumberOfTrades().longValue());
    gen.writeBooleanField("x", candlestickEvent.getBarFinal().booleanValue());
    gen.writeStringField("q", candlestickEvent.getQuoteAssetVolume());
    gen.writeStringField("V", candlestickEvent.getTakerBuyBaseAssetVolume());
    gen.writeStringField("Q", candlestickEvent.getTakerBuyQuoteAssetVolume());
    gen.writeEndObject();
  }
}
