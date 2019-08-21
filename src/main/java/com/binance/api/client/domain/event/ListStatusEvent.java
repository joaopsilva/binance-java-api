package com.binance.api.client.domain.event;

import com.binance.api.client.domain.account.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListStatusEvent {

    @JsonProperty("e")
    private String eventType;

    @JsonProperty("E")
    private long eventTime;

    @JsonProperty("s")
    private String symbol;

    @JsonProperty("g")
    private long orderListId;

    @JsonProperty("c")
    private String contingencyType;

    @JsonProperty("l")
    private String listStatusType;

    @JsonProperty("L")
    private String listOrderStatus;

    @JsonProperty("r")
    private String listRejectReason;

    @JsonProperty("C")
    private String listClientOrderId;

    @JsonProperty("T")
    private long transactionTime;

    @JsonProperty("O")
    @JsonDeserialize(contentUsing = OrderDeserializer.class)
    private List<Order> orders;

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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getOrderListId() {
        return orderListId;
    }

    public void setOrderListId(long orderListId) {
        this.orderListId = orderListId;
    }

    public String getContingencyType() {
        return contingencyType;
    }

    public void setContingencyType(String contingencyType) {
        this.contingencyType = contingencyType;
    }

    public String getListStatusType() {
        return listStatusType;
    }

    public void setListStatusType(String listStatusType) {
        this.listStatusType = listStatusType;
    }

    public String getListOrderStatus() {
        return listOrderStatus;
    }

    public void setListOrderStatus(String listOrderStatus) {
        this.listOrderStatus = listOrderStatus;
    }

    public String getListRejectReason() {
        return listRejectReason;
    }

    public void setListRejectReason(String listRejectReason) {
        this.listRejectReason = listRejectReason;
    }

    public String getListClientOrderId() {
        return listClientOrderId;
    }

    public void setListClientOrderId(String listClientOrderId) {
        this.listClientOrderId = listClientOrderId;
    }

    public long getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(long transactionTime) {
        this.transactionTime = transactionTime;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "ListStatusEvent{" +
                "eventType='" + eventType + '\'' +
                ", eventTime=" + eventTime +
                ", symbol='" + symbol + '\'' +
                ", orderListId=" + orderListId +
                ", contingencyType='" + contingencyType + '\'' +
                ", listStatusType='" + listStatusType + '\'' +
                ", listOrderStatus='" + listOrderStatus + '\'' +
                ", listRejectReason='" + listRejectReason + '\'' +
                ", listClientOrderId='" + listClientOrderId + '\'' +
                ", transactionTime=" + transactionTime +
                ", orders=" + orders +
                '}';
    }
}
