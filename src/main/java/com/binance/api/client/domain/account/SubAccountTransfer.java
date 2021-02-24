package com.binance.api.client.domain.account;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubAccountTransfer {

    /**
     * Counter party name
     */
    private String counterParty;

    /**
     * Counter party email
     */
    private String email;

    /**
     * Transfer in or transfer out
     */
    private Integer type; // 1 for transfer in, 2 for transfer out

    /**
     * Transfer asset
     */
    private String asset;

    /**
     * Quantity of transfer asset
     */
    private String qty;

    /**
     * Transfer status
     */
    private String status;

    /**
     * Transfer ID
     */
    private Long tranId;

    /**
     * Transfer time
     */
    private Long time;

    // Setter
    public void setCounterParty(String counterParty) {
        this.counterParty = counterParty;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTranId(Long tranId) {
        this.tranId = tranId;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    // Getter
    public String getCounterParty() {
        return this.counterParty;
    }

    public String getEmail() {
        return this.email;
    }

    public Integer getType() {
        return this.type;
    }

    public String getAsset() {
        return this.asset;
    }

    public String getQty() {
        return this.qty;
    }

    public String getStatus() {
        return this.status;
    }

    public Long getTranId() {
        return this.tranId;
    }

    public Long getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("counterParty", this.counterParty)
                .append("email", this.email)
                .append("type", this.type)
                .append("asset", this.asset)
                .append("qty", this.qty)
                .append("status", this.status)
                .append("tranId", this.tranId)
                .append("time", this.time)
                .toString();
    }

}