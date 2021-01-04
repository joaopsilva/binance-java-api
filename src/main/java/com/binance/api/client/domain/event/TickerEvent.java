package com.binance.api.client.domain.event;

import com.binance.api.client.constant.BinanceApiConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerEvent {

    @JsonProperty("e")
    private String eventType;

    @JsonProperty("E")
    private long eventTime;

    @JsonProperty("s")
    private String symbol;

    @JsonProperty("p")
    private String priceChange;

    @JsonProperty("P")
    private String priceChangePercent;

    @JsonProperty("w")
    private String weightedAveragePrice;

    @JsonProperty("x")
    private String previousDaysClosePrice;

    @JsonProperty("c")
    private String currentDaysClosePrice;

    @JsonProperty("Q")
    private String closeTradesQuantity;

    @JsonProperty("b")
    private String bestBidPrice;

    @JsonProperty("B")
    private String bestBidQuantity;

    @JsonProperty("a")
    private String bestAskPrice;

    @JsonProperty("A")
    private String bestAskQuantity;

    @JsonProperty("o")
    private String openPrice;

    @JsonProperty("h")
    private String highPrice;

    @JsonProperty("l")
    private String lowPrice;

    @JsonProperty("v")
    private String totalTradedBaseAssetVolume;

    @JsonProperty("q")
    private String totalTradedQuoteAssetVolume;

    @JsonProperty("O")
    private long statisticsOpenTime;

    @JsonProperty("C")
    private long statisticsCloseTime;

    @JsonProperty("F")
    private long firstTradeId;

    @JsonProperty("L")
    private long lastTradeId;

    @JsonProperty("n")
    private long totalNumberOfTrades;

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

    public String getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(String priceChange) {
        this.priceChange = priceChange;
    }

    public String getPriceChangePercent() {
        return priceChangePercent;
    }

    public void setPriceChangePercent(String priceChangePercent) {
        this.priceChangePercent = priceChangePercent;
    }

    public String getWeightedAveragePrice() {
        return weightedAveragePrice;
    }

    public void setWeightedAveragePrice(String weightedAveragePrice) {
        this.weightedAveragePrice = weightedAveragePrice;
    }

    public String getPreviousDaysClosePrice() {
        return previousDaysClosePrice;
    }

    public void setPreviousDaysClosePrice(String previousDaysClosePrice) {
        this.previousDaysClosePrice = previousDaysClosePrice;
    }

    public String getCurrentDaysClosePrice() {
        return currentDaysClosePrice;
    }

    public void setCurrentDaysClosePrice(String currentDaysClosePrice) {
        this.currentDaysClosePrice = currentDaysClosePrice;
    }

    public String getCloseTradesQuantity() {
        return closeTradesQuantity;
    }

    public void setCloseTradesQuantity(String closeTradesQuantity) {
        this.closeTradesQuantity = closeTradesQuantity;
    }

    public String getBestBidPrice() {
        return bestBidPrice;
    }

    public void setBestBidPrice(String bestBidPrice) {
        this.bestBidPrice = bestBidPrice;
    }

    public String getBestBidQuantity() {
        return bestBidQuantity;
    }

    public void setBestBidQuantity(String bestBidQuantity) {
        this.bestBidQuantity = bestBidQuantity;
    }

    public String getBestAskPrice() {
        return bestAskPrice;
    }

    public void setBestAskPrice(String bestAskPrice) {
        this.bestAskPrice = bestAskPrice;
    }

    public String getBestAskQuantity() {
        return bestAskQuantity;
    }

    public void setBestAskQuantity(String bestAskQuantity) {
        this.bestAskQuantity = bestAskQuantity;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getTotalTradedBaseAssetVolume() {
        return totalTradedBaseAssetVolume;
    }

    public void setTotalTradedBaseAssetVolume(String totalTradedBaseAssetVolume) {
        this.totalTradedBaseAssetVolume = totalTradedBaseAssetVolume;
    }

    public String getTotalTradedQuoteAssetVolume() {
        return totalTradedQuoteAssetVolume;
    }

    public void setTotalTradedQuoteAssetVolume(String totalTradedQuoteAssetVolume) {
        this.totalTradedQuoteAssetVolume = totalTradedQuoteAssetVolume;
    }

    public long getStatisticsOpenTime() {
        return statisticsOpenTime;
    }

    public void setStatisticsOpenTime(long statisticsOpenTime) {
        this.statisticsOpenTime = statisticsOpenTime;
    }

    public long getStatisticsCloseTime() {
        return statisticsCloseTime;
    }

    public void setStatisticsCloseTime(long statisticsCloseTime) {
        this.statisticsCloseTime = statisticsCloseTime;
    }

    public long getFirstTradeId() {
        return firstTradeId;
    }

    public void setFirstTradeId(long firstTradeId) {
        this.firstTradeId = firstTradeId;
    }

    public long getLastTradeId() {
        return lastTradeId;
    }

    public void setLastTradeId(long lastTradeId) {
        this.lastTradeId = lastTradeId;
    }

    public long getTotalNumberOfTrades() {
        return totalNumberOfTrades;
    }

    public void setTotalNumberOfTrades(long totalNumberOfTrades) {
        this.totalNumberOfTrades = totalNumberOfTrades;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("eventType", eventType)
                .append("eventTime", eventTime)
                .append("symbol", symbol)
                .append("priceChange", priceChange)
                .append("priceChangePercent", priceChangePercent)
                .append("weightedAveragePrice", weightedAveragePrice)
                .append("previousDaysClosePrice", previousDaysClosePrice)
                .append("currentDaysClosePrice", currentDaysClosePrice)
                .append("closeTradesQuantity", closeTradesQuantity)
                .append("bestBidPrice", bestBidPrice)
                .append("bestBidQuantity", bestBidQuantity)
                .append("bestAskPrice", bestAskPrice)
                .append("bestAskQuantity", bestAskQuantity)
                .append("openPrice", openPrice)
                .append("highPrice", highPrice)
                .append("lowPrice", lowPrice)
                .append("totalTradedBaseAssetVolume", totalTradedBaseAssetVolume)
                .append("totalTradedQuoteAssetVolume", totalTradedQuoteAssetVolume)
                .append("statisticsOpenTime", statisticsOpenTime)
                .append("statisticsCloseTime", statisticsCloseTime)
                .append("firstTradeId", firstTradeId)
                .append("lastTradeId", lastTradeId)
                .append("totalNumberOfTrades", totalNumberOfTrades)
                .toString();
    }
}
