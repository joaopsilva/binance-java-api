package com.binance.api.client.domain.account;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Dust transfer result information.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferResult {

    private String amount;

    private String fromAsset;

    /**
     * Order timestamp.
     */
    private long operateTime;

    private String serviceChargeAmount;

    private long tranId;

    private String transferedAmount;



    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFromAsset() {
        return fromAsset;
    }

    public void setFromAsset(String fromAsset) {
        this.fromAsset = fromAsset;
    }

    public long getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(long operateTime) {
        this.operateTime = operateTime;
    }

    public String getServiceChargeAmount() {
        return serviceChargeAmount;
    }

    public void setServiceChargeAmount(String serviceChargeAmount) {
        this.serviceChargeAmount = serviceChargeAmount;
    }

    public long getTranId() {
        return tranId;
    }

    public void setTranId(long tranId) {
        this.tranId = tranId;
    }

    public String getTransferedAmount() {
        return transferedAmount;
    }

    public void setTransferedAmount(String transferedAmount) {
        this.transferedAmount = transferedAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("amount", amount)
                .append("fromAsset", fromAsset)
                .append("operateTime", operateTime)
                .append("serviceChargeAmount", serviceChargeAmount)
                .append("tranId", tranId)
                .append("transderedAmount", transferedAmount)
                .toString();
    }

}