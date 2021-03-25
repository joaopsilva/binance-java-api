package com.binance.api.client.domain.account.request;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Response object returned when an order is canceled.
 *
 * @see CancelOrderRequest for the request
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelOrderResponse {

  private String symbol;

  private String origClientOrderId;

  private Long orderId;

  private String clientOrderId;

  private OrderStatus status;

  private String executedQty;

  public String getSymbol() {
    return symbol;
  }

  public CancelOrderResponse setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public String getOrigClientOrderId() {
    return origClientOrderId;
  }

  public CancelOrderResponse setOrigClientOrderId(String origClientOrderId) {
    this.origClientOrderId = origClientOrderId;
    return this;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setExecutedQty(String executedQty) {
    this.executedQty = executedQty;
  }

  public String getExecutedQty() {
    return executedQty;
  }

  public Long getOrderId() {
    return orderId;
  }

  public CancelOrderResponse setOrderId(Long orderId) {
    this.orderId = orderId;
    return this;
  }

  public String getClientOrderId() {
    return clientOrderId;
  }

  public CancelOrderResponse setClientOrderId(String clientOrderId) {
    this.clientOrderId = clientOrderId;
    return this;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
            .append("symbol", symbol)
            .append("origClientOrderId", origClientOrderId)
            .append("orderId", orderId)
            .append("clientOrderId", clientOrderId)
            .toString();
  }
}