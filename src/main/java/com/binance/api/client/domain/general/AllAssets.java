package com.binance.api.client.domain.general;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * All assets Binance supports.
 */
 @JsonIgnoreProperties(ignoreUnknown = true)
 public class AllAssets {

   @JsonProperty("id")
   private String id;

   @JsonProperty("assetCode")
   private String assetCode;

   @JsonProperty("assetName")
   private String assetName;

   @JsonProperty("unit")
   private String unit;

   @JsonProperty("transactionFee")
   private long transactionFee;

   @JsonProperty("commissionRate")
   private long commissionRate;

   @JsonProperty("freeAuditWithdrawAmt")
   private long freeAuditWithdrawAmount;

   @JsonProperty("freeUserChargeAmount")
   private long freeUserChargeAmount;

   @JsonProperty("minProductWithdraw")
   private long minProductWithdraw;

   @JsonProperty("withdrawIntegerMultiple")
   private long withdrawIntegerMultiple;

   @JsonProperty("confirmTimes")
   private long confirmTimes;

   @JsonProperty("enableWithdraw")
   private boolean enableWithdraw;

   @JsonProperty("isLegalMoney")
   private boolean isLegalMoney;

   public String getId() {
     return id;
   }

   public String getAssetCode() {
     return assetCode;
   }

   public String getAssetName() {
     return assetName;
   }

   public String getUnit() {
     return unit;
   }

   public long getTransactionFee() {
     return transactionFee;
   }

   public long getCommissionRate() {
     return commissionRate;
   }

   public long getFreeAuditWithdrawAmount() {
     return freeAuditWithdrawAmount;
   }

   public long getFreeUserChargeAmount() {
     return freeUserChargeAmount;
   }

   public long minProductWithdraw() {
     return minProductWithdraw;
   }

   public long getWithdrawIntegerMultiple() {
     return withdrawIntegerMultiple;
   }

   public long getConfirmTimes() {
     return confirmTimes;
   }

   public long canWithraw() {
     return enableWithdraw;
   }

   public long isLegalMoney() {
     return isLegalMoney;
   }

   @Override
   public String toString() {
     return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
         .append("id", id)
         .append("assetCode", assetCode)
         .append("assetName", assetName)
         .append("unit", unit)
         .append("transactionFee", transactionFee)
         .append("commissionRate", commissionRate)
         .append("freeAuditWithdrawAmount", freeAuditWithdrawAmount)
         .append("freeUserChargeAmount", freeUserChargeAmount)
         .append("minProductWithdraw", minProductWithdraw)
         .append("withdrawIntegerMultiple", withdrawIntegerMultiple)
         .append("confirmTimes", confirmTimes)
         .append("enableWithdraw", enableWithdraw)
         .append("isLegalMoney", isLegalMoney)
         .toString();
   }
 }
