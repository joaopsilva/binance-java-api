package com.binance.api.client.domain.general;

/**
 * Status of a symbol on the exchange.
 */
public enum SymbolStatus {
  PRE_TRADING,
  TRADING,
  POST_TRADING,
  END_OF_DAY,
  HALT,
  AUCTION_MATCH,
  BREAK;
}
