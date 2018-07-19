package com.binance.api.client.domain.general;

/**
 * Filters define trading rules on a symbol or an exchange. Filters come in two forms: symbol filters and exchange filters.
 */
public enum FilterType {
  // Symbol
  PRICE_FILTER,
  LOT_SIZE,
  MIN_NOTIONAL,
  MAX_NUM_ORDERS,
  MAX_ALGO_ORDERS,
  MAX_NUM_ALGO_ORDERS,

  // Exchange
  EXCHANGE_MAX_NUM_ORDERS,
  EXCHANGE_MAX_ALGO_ORDERS
}
