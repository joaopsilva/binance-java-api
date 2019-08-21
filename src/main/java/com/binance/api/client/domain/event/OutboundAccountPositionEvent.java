package com.binance.api.client.domain.event;

import com.binance.api.client.domain.account.AssetBalance;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OutboundAccountPositionEvent {

    @JsonProperty("e")
    private String eventType;

    @JsonProperty("E")
    private long eventTime;

    @JsonProperty("u")
    private long lastAccountUpdate;

    @JsonProperty("B")
    @JsonDeserialize(contentUsing = AssetBalanceDeserializer.class)
    private List<AssetBalance> balances;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public long getEventTime() {
        return eventTime;
    }

    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }

    public long getLastAccountUpdate() {
        return lastAccountUpdate;
    }

    public void setLastAccountUpdate(long lastAccountUpdate) {
        this.lastAccountUpdate = lastAccountUpdate;
    }

    public List<AssetBalance> getBalances() {
        return balances;
    }

    public void setBalances(List<AssetBalance> balances) {
        this.balances = balances;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("eventType", eventType)
                .append("eventTime", eventTime)
                .append("lastAccountUpdate", lastAccountUpdate)
                .append("balances", balances)
                .toString();
    }
}
