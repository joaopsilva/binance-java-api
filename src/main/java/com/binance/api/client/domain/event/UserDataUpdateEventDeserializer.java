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

  private ObjectMapper mapper;

  @Override
  public UserDataUpdateEvent deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {

    if (mapper == null){
      mapper = new ObjectMapper();
    }

    ObjectCodec oc = jp.getCodec();
    JsonNode node = oc.readTree(jp);
    String json = node.toString();

    final String eventTypeId = node.get("e").asText();
    final Long eventTime = node.get("E").asLong();
    UserDataUpdateEventType userDataUpdateEventType = UserDataUpdateEventType.fromEventTypeId(eventTypeId);

    UserDataUpdateEvent userDataUpdateEvent = new UserDataUpdateEvent();
    userDataUpdateEvent.setEventType(userDataUpdateEventType);
    userDataUpdateEvent.setEventTime(eventTime);

    if (userDataUpdateEventType == UserDataUpdateEventType.ACCOUNT_UPDATE) {
      AccountUpdateEvent accountUpdateEvent = getUserDataUpdateEventDetail(json, AccountUpdateEvent.class, mapper);
      userDataUpdateEvent.setAccountUpdateEvent(accountUpdateEvent);
    } else { // userDataUpdateEventType == UserDataUpdateEventType.ORDER_TRADE_UPDATE
      OrderTradeUpdateEvent orderTradeUpdateEvent = getUserDataUpdateEventDetail(json, OrderTradeUpdateEvent.class, mapper);
      userDataUpdateEvent.setOrderTradeUpdateEvent(orderTradeUpdateEvent);
    }

    return userDataUpdateEvent;
  }

  public <T> T getUserDataUpdateEventDetail(String json, Class<T> clazz, ObjectMapper mapper) {
    try {
      return mapper.readValue(json, clazz);
    } catch (IOException e) {
      throw new BinanceApiException(e);
    }
  }
}