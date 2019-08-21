package com.binance.api.client.domain.event;

import com.binance.api.client.domain.account.Order;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class OrderDeserializer extends JsonDeserializer<Order> {

    @Override
    public Order deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);
        final String symbol = node.get("s").asText();
        final long orderId = node.get("i").asLong();
        final String clientOrderId = node.get("c").asText();

        Order order = new Order();
        order.setSymbol(symbol);
        order.setOrderId(orderId);
        order.setClientOrderId(clientOrderId);
        return order;
    }
}