package com.binance.api.client.domain.event;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.exception.UnsupportedEventException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User data update event which can be of four types:
 * <p>
 * 1) outboundAccountInfo, whenever there is a change in the account (e.g. balance of an asset)
 * 2) outboundAccountPosition, the change in account balances caused by an event.
 * 3) executionReport, whenever there is a trade or an order
 * 4) balanceUpdate, the change in account balance (delta).
 * <p>
 * Deserialization could fail with UnsupportedEventException in case of unsupported eventType.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = UserDataUpdateEventDeserializer.class)
public class UserDataUpdateEvent {

  private UserDataUpdateEventType eventType;

  private long eventTime;

  private AccountUpdateEvent outboundAccountPositionUpdateEvent;

  private BalanceUpdateEvent balanceUpdateEvent;

  private OrderTradeUpdateEvent orderTradeUpdateEvent;

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

  /**
   * @Deprecated: left in for backwards compatibility. Use getOutboundAccountPositionUpdateEvent() instead, as that is what the Binance API documentation calls it.
   */
  @Deprecated
  public AccountUpdateEvent getAccountUpdateEvent() {
    return outboundAccountPositionUpdateEvent;
  }

  public AccountUpdateEvent getOutboundAccountPositionUpdateEvent() {
    return outboundAccountPositionUpdateEvent;
  }

  public void setOutboundAccountPositionUpdateEvent(AccountUpdateEvent accountUpdateEvent) {
    this.outboundAccountPositionUpdateEvent = accountUpdateEvent;
  }

  public BalanceUpdateEvent getBalanceUpdateEvent() {
    return balanceUpdateEvent;
  }

  public void setBalanceUpdateEvent(BalanceUpdateEvent balanceUpdateEvent) {
    this.balanceUpdateEvent = balanceUpdateEvent;
  }

  public OrderTradeUpdateEvent getOrderTradeUpdateEvent() {
    return orderTradeUpdateEvent;
  }

  public void setOrderTradeUpdateEvent(OrderTradeUpdateEvent orderTradeUpdateEvent) {
    this.orderTradeUpdateEvent = orderTradeUpdateEvent;
  }

  @Override
  public String toString() {
    ToStringBuilder sb = new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
        .append("eventType", eventType)
        .append("eventTime", eventTime);
    if (eventType == UserDataUpdateEventType.ACCOUNT_POSITION_UPDATE) {
      sb.append("outboundAccountPositionUpdateEvent", outboundAccountPositionUpdateEvent);
    } else if (eventType == UserDataUpdateEventType.BALANCE_UPDATE) {
      sb.append("balanceUpdateEvent", balanceUpdateEvent);
    } else {
      sb.append("orderTradeUpdateEvent", orderTradeUpdateEvent);
    }
    return sb.toString();
  }

  public enum UserDataUpdateEventType {
    /** Corresponds to "outboundAccountPosition" events. */
    ACCOUNT_POSITION_UPDATE("outboundAccountPosition"),
    /** Corresponds to "balanceUpdate" events. */
    BALANCE_UPDATE("balanceUpdate"),
    /** Corresponds to "executionReport" events. */
    ORDER_TRADE_UPDATE("executionReport"),
    ;

    private final String eventTypeId;

    UserDataUpdateEventType(String eventTypeId) {
      this.eventTypeId = eventTypeId;
    }

    public String getEventTypeId() {
      return eventTypeId;
    }

    public static UserDataUpdateEventType fromEventTypeId(String eventTypeId) {
      if (ORDER_TRADE_UPDATE.eventTypeId.equals(eventTypeId)) {
        return ORDER_TRADE_UPDATE;
      } else if (ACCOUNT_POSITION_UPDATE.eventTypeId.equals(eventTypeId)) {
        return ACCOUNT_POSITION_UPDATE;
      } else if (BALANCE_UPDATE.eventTypeId.equals(eventTypeId)) {
        return BALANCE_UPDATE;
      }
      throw new UnsupportedEventException("Unrecognized user data update event type id: " + eventTypeId);
    }
  }
}
