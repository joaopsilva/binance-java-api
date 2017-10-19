package com.binance.api.client.domain;

/**
 * Order reject reason values.
 */
public enum OrderRejectReason {
  NONE,
  UNKNOWN_INSTRUMENT,
  MARKET_CLOSED,
  PRICE_QTY_EXCEED_HARD_LIMITS,
  UNKNOWN_ORDER,
  DUPLICATE_ORDER,
  UNKNOWN_ACCOUNT,
  INSUFFICIENT_BALANCE,
  ACCOUNT_INACTIVE,
  ACCOUNT_CANNOT_SETTLE
}