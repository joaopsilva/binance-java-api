package com.binance.api.client.domain.account;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DustTransferResponse {

    private String totalServiceCharge;

    private String totalTransfered;

    private List<TransferResult> transferResult;

    public String getTotalServiceCharge() {
        return totalServiceCharge;
    }

    public void setTotalServiceCharge(String totalServiceCharge) {
        this.totalServiceCharge = totalServiceCharge;
    }

    public String getTotalTransfered() {
        return totalTransfered;
    }

    public void setTotalTransfered(String totalTransfered) {
        this.totalTransfered = totalTransfered;
    }

    public List<TransferResult> getTransferResult() {
        return transferResult;
    }

    public void setTransferResult(List<TransferResult> transferResult) {
        this.transferResult = transferResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("totalServiceCharge", totalServiceCharge)
                .append("totalTransfered", totalTransfered)
                .append("transferResult", transferResult)
                .toString();
    }

}