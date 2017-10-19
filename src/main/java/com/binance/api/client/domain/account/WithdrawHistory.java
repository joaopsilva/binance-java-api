package com.binance.api.client.domain.account;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * History of account withdrawals.
 *
 * @see Withdraw
 */
public class WithdrawHistory {

  private List<Withdraw> withdrawList;

  private boolean success;

  public List<Withdraw> getWithdrawList() {
    return withdrawList;
  }

  public void setWithdrawList(List<Withdraw> withdrawList) {
    this.withdrawList = withdrawList;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("withdrawList", withdrawList)
        .append("success", success)
        .toString();
  }
}
