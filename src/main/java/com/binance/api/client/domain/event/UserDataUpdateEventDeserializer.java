package com.binance.api.client.domain.event;

import com.binance.api.client.domain.event.UserDataUpdateEvent.UserDataUpdateEventType;
import com.binance.api.client.exception.BinanceApiException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Custom deserializer for a User Data stream event, since the API can return two different responses in this stream.
 * @see UserDataUpdateEvent
 */
public class UserDataUpdateEventDeserializer extends JsonDeserializer<UserDataUpdateEvent> {

  @Override
  public UserDataUpdateEvent deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
    ObjectCodec oc = jp.getCodec();
    JsonNode node = oc.readTree(jp);
    String json = node.toString();

    final String eventTypeId = node.get("e").asText();
    final long eventTime = node.get("E").asLong();
    UserDataUpdateEventType userDataUpdateEventType = UserDataUpdateEventType.fromEventTypeId(eventTypeId);

    UserDataUpdateEvent userDataUpdateEvent = new UserDataUpdateEvent();
    userDataUpdateEvent.setEventType(userDataUpdateEventType);
    userDataUpdateEvent.setEventTime(eventTime);

    switch (userDataUpdateEventType) {
      case ACCOUNT_UPDATE:
        AccountUpdateEvent accountUpdateEvent = getUserDataUpdateEventDetail(json, AccountUpdateEvent.class);
        userDataUpdateEvent.setAccountUpdateEvent(accountUpdateEvent);
        break;
      case ORDER_TRADE_UPDATE:
        OrderTradeUpdateEvent orderTradeUpdateEvent = getUserDataUpdateEventDetail(json, OrderTradeUpdateEvent.class);
        userDataUpdateEvent.setOrderTradeUpdateEvent(orderTradeUpdateEvent);
        break;
      case OUTBOUND_ACCOUNT_POSITION:
        OutboundAccountPositionEvent outboundAccountPositionEvent = getUserDataUpdateEventDetail(json, OutboundAccountPositionEvent.class);
        userDataUpdateEvent.setOutboundAccountPositionEvent(outboundAccountPositionEvent);
        break;
      case LIST_STATUS:
        ListStatusEvent listStatusEvent = getUserDataUpdateEventDetail(json, ListStatusEvent.class);
        userDataUpdateEvent.setListStatusEvent(listStatusEvent);
        break;
    }

    return userDataUpdateEvent;
  }

  private <T> T getUserDataUpdateEventDetail(String json, Class<T> clazz) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readValue(json, clazz);
    } catch (IOException e) {
      throw new BinanceApiException(e);
    }
  }
}