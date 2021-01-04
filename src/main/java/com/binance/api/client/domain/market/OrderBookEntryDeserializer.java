package com.binance.api.client.domain.market;

import com.binance.api.client.domain.market.OrderBookEntry;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * Custom deserializer for an OrderBookEntry, since the API returns an array in the format [ price, qty, [] ].
 */
public class OrderBookEntryDeserializer extends JsonDeserializer<OrderBookEntry> {

  @Override
  public OrderBookEntry deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
    ObjectCodec oc = jp.getCodec();
    JsonNode node = oc.readTree(jp);
    final String price = node.get(0).asText();
    final String qty = node.get(1).asText();

    OrderBookEntry orderBookEntry = new OrderBookEntry();
    orderBookEntry.setPrice(price);
    orderBookEntry.setQty(qty);
    return orderBookEntry;
  }
}
