package com.binance.api.client.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * User data update event which can be of two types:
 *
 * 1) outboundAccountInfo, whenever there is a change in the account (e.g. balance of an asset)
 * 2) executionReport, whenever there is a trade or an order
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = UserDataUpdateEventDeserializer.class)
public class UserDataUpdateEvent {

  private UserDataUpdateEventType eventType;

  private long eventTime;

  private AccountUpdateEvent accountUpdateEvent;

  private OrderTradeUpdateEvent orderTradeUpdateEvent;

  private OutboundAccountPositionEvent outboundAccountPositionEvent;

  private ListStatusEvent listStatusEvent;

  public UserDataUpdateEventType getEventType() {
    return eventType;
  }

  public void setEventType(UserDataUpdateEventType eventType) {
    this.eventType = eventType;
  }

  public long getEventTime() {
    return eventTime;
  }

  public void setEventTime(long eventTime) {
    this.eventTime = eventTime;
  }

  public AccountUpdateEvent getAccountUpdateEvent() {
    return accountUpdateEvent;
  }

  public void setAccountUpdateEvent(AccountUpdateEvent accountUpdateEvent) {
    this.accountUpdateEvent = accountUpdateEvent;
  }

  public OrderTradeUpdateEvent getOrderTradeUpdateEvent() {
    return orderTradeUpdateEvent;
  }

  public void setOrderTradeUpdateEvent(OrderTradeUpdateEvent orderTradeUpdateEvent) {
    this.orderTradeUpdateEvent = orderTradeUpdateEvent;
  }

  public OutboundAccountPositionEvent getOutboundAccountPositionEvent() {
    return outboundAccountPositionEvent;
  }

  public void setOutboundAccountPositionEvent(OutboundAccountPositionEvent outboundAccountPositionEvent) {
    this.outboundAccountPositionEvent = outboundAccountPositionEvent;
  }

  public ListStatusEvent getListStatusEvent() {
    return listStatusEvent;
  }

  public void setListStatusEvent(ListStatusEvent listStatusEvent) {
    this.listStatusEvent = listStatusEvent;
  }

  @Override
  public String toString() {
    ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("eventType", eventType)
            .append("eventTime", eventTime);
    if (eventType == UserDataUpdateEventType.ACCOUNT_UPDATE) {
      sb.append("accountUpdateEvent", accountUpdateEvent);
    } else if (eventType == UserDataUpdateEventType.ORDER_TRADE_UPDATE) {
      sb.append("orderTradeUpdateEvent", orderTradeUpdateEvent);
    } else if (eventType == UserDataUpdateEventType.OUTBOUND_ACCOUNT_POSITION) {
      sb.append("outboundAccountPositionEvent", outboundAccountPositionEvent);
    } else if (eventType == UserDataUpdateEventType.LIST_STATUS) {
      sb.append("listStatusEvent", listStatusEvent);
    }
    return sb.toString();
  }

  public enum UserDataUpdateEventType {
    ACCOUNT_UPDATE("outboundAccountInfo"),
    ORDER_TRADE_UPDATE("executionReport"),
    OUTBOUND_ACCOUNT_POSITION("outboundAccountPosition"),
    LIST_STATUS("listStatus");

    private final String eventTypeId;

    UserDataUpdateEventType(String eventTypeId) {
      this.eventTypeId = eventTypeId;
    }

    public String getEventTypeId() {
      return eventTypeId;
    }

    public static UserDataUpdateEventType fromEventTypeId(String eventTypeId) {
      if (ACCOUNT_UPDATE.eventTypeId.equals(eventTypeId)) {
        return ACCOUNT_UPDATE;
      } else if (ORDER_TRADE_UPDATE.eventTypeId.equals(eventTypeId)) {
        return ORDER_TRADE_UPDATE;
      } else if (OUTBOUND_ACCOUNT_POSITION.eventTypeId.equals(eventTypeId)) {
        return OUTBOUND_ACCOUNT_POSITION;
      } else if (LIST_STATUS.eventTypeId.equals(eventTypeId)) {
        return LIST_STATUS;
      }

      throw new IllegalArgumentException("Unrecognized user data update event type id: " + eventTypeId);
    }
  }
}
