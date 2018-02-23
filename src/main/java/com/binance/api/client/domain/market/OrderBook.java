package com.binance.api.client.domain.market;

import com.binance.api.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Order book of a symbol in Binance.
 */
public class OrderBook {

  /**
   * Last update id of this order book.
   */
  private long lastUpdateId;

  /**
   * List of bids (price/qty).
   */
  private List<OrderBookEntry> bids;

  /**
   * List of asks (price/qty).
   */
  private List<OrderBookEntry> asks;

  public long getLastUpdateId() {
    return lastUpdateId;
  }

  public void setLastUpdateId(long lastUpdateId) {
    this.lastUpdateId = lastUpdateId;
  }

  public List<OrderBookEntry> getBids() {
    return bids;
  }

  public void setBids(List<OrderBookEntry> bids) {
    this.bids = bids;
  }

  public List<OrderBookEntry> getAsks() {
    return asks;
  }

  public void setAsks(List<OrderBookEntry> asks) {
    this.asks = asks;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
        .append("lastUpdateId", lastUpdateId)
        .append("bids", bids)
        .append("asks", asks)
        .toString();
  }
}
