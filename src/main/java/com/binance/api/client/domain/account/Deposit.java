package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * A deposit that was done to a Binance account.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Deposit {

  /**
   * Amount deposited.
   */
  private String amount;

  /**
   * Symbol.
   */
  private String asset;

  /**
   * Deposit time.
   */
  private String insertTime;

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getAsset() {
    return asset;
  }

  public void setAsset(String asset) {
    this.asset = asset;
  }

  public String getInsertTime() {
    return insertTime;
  }

  public void setInsertTime(String insertTime) {
    this.insertTime = insertTime;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("amount", amount)
        .append("asset", asset)
        .append("insertTime", insertTime)
        .toString();
  }
}
