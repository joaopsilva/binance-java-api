package com.binance.api.client.domain.account;

import java.util.List;

import com.binance.api.client.domain.ContingencyType;
import com.binance.api.client.domain.OCOOrderStatus;
import com.binance.api.client.domain.OCOStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewOCOResponse extends OrderList {

    private Long orderListId;
    private ContingencyType contingencyType;
    private OCOStatus listStatusType;
    private OCOOrderStatus listOrderStatus;
    private String listClientOrderId;
    private Long transactionTime;
    private String symbol;
    private List<OrderReport> orderReports;

    // Getters
    public Long getOrderListId() {
        return this.orderListId;
    }

    public ContingencyType getContingencyType() {
        return this.contingencyType;
    }

    public OCOStatus getListStatusType() {
        return this.listStatusType;
    }

    public OCOOrderStatus getListOrderStatus() {
        return this.listOrderStatus;
    }

    public String getListClientOrderId() {
        return this.listClientOrderId;
    }

    public Long getTransactionTime() {
        return this.transactionTime;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public List<OrderReport> getOrderReports() {
        return orderReports;
    }

    // Setter
    public void setOrderListId(Long orderListId) {
        this.orderListId = orderListId;
    }

    public void setContingencyType(ContingencyType contingencyType) {
        this.contingencyType = contingencyType;
    }

    public void setListStatusType(OCOStatus listStatusType) {
        this.listStatusType = listStatusType;
    }

    public void setListOrderStatus(OCOOrderStatus listOrderStatus) {
        this.listOrderStatus = listOrderStatus;
    }

    public void setListClientOrderId(String listClientOrderId) {
        this.listClientOrderId = listClientOrderId;
    }

    public void setTransactionTime(Long transactionTime) {
        this.transactionTime = transactionTime;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setOrderReports(List<OrderReport> orderReports) {
        this.orderReports = orderReports;
    }

}
