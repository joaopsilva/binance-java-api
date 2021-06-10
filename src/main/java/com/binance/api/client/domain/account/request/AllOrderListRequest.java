package com.binance.api.client.domain.account.request;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AllOrderListRequest {

    private static final Integer DEFAULT_LIMIT = 500;

    private Long fromId;

    private Long startTime;

    private Long endTime;

    private Integer limit;

    private Long recvWindow;

    private Long timestamp;

    public AllOrderListRequest() {
        this.limit = DEFAULT_LIMIT;
        this.timestamp = System.currentTimeMillis();
        this.recvWindow = BinanceApiConstants.DEFAULT_RECEIVING_WINDOW;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Long getRecvWindow() {
        return recvWindow;
    }

    public void setRecvWindow(Long recvWindow) {
        this.recvWindow = recvWindow;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}