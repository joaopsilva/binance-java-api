package com.binance.api.client.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AllMarketTickersEvent {

  @JsonProperty("e")
  private String eventType;

  @JsonProperty("E")
  private long eventTime;

  @JsonProperty("s")
  private String symbol;

  @JsonProperty("p")
  private double priceChange;

  @JsonProperty("P")
  private double priceChangePercent;

  @JsonProperty("w")
  private double weightedAveragePrice;

  @JsonProperty("x")
  private double previousDaysClosePrice;

  @JsonProperty("c")
  private double currentDaysClosePrice;

  @JsonProperty("Q")
  private long closeTradesQuantity;

  @JsonProperty("a")
  private double bestAskPrice;

  @JsonProperty("A")
  private long bestAskQuantity;

  @JsonProperty("o")
  private double openPrice;

  @JsonProperty("h")
  private double highPrice;

  @JsonProperty("l")
  private double lowPrice;

  @JsonProperty("v")
  private long totalTradedBaseAssetVolume;

  @JsonProperty("q")
  private long totalTradedQuoteAssetVolume;

  @JsonProperty("O")
  private long statisticesOpenTime;

  @JsonProperty("C")
  private long statisticesCloseTime;

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

  public double getPriceChange() {
    return priceChange;
  }

  public void setPriceChange(double priceChange) {
    this.priceChange = priceChange;
  }

  public double getPriceChangePercent() {
    return priceChangePercent;
  }

  public void setPriceChangePercent(double priceChangePercent) {
    this.priceChangePercent = priceChangePercent;
  }

  public double getWeightedAveragePrice() {
    return weightedAveragePrice;
  }

  public void setWeightedAveragePrice(double weightedAveragePrice) {
    this.weightedAveragePrice = weightedAveragePrice;
  }

  public double getPreviousDaysClosePrice() {
    return previousDaysClosePrice;
  }

  public void setPreviousDaysClosePrice(double previousDaysClosePrice) {
    this.previousDaysClosePrice = previousDaysClosePrice;
  }

  public double getCurrentDaysClosePrice() {
    return currentDaysClosePrice;
  }

  public void setCurrentDaysClosePrice(double currentDaysClosePrice) {
    this.currentDaysClosePrice = currentDaysClosePrice;
  }

  public long getCloseTradesQuantity() {
    return closeTradesQuantity;
  }

  public void setCloseTradesQuantity(long closeTradesQuantity) {
    this.closeTradesQuantity = closeTradesQuantity;
  }

  public double getBestAskPrice() {
    return bestAskPrice;
  }

  public void setBestAskPrice(double bestAskPrice) {
    this.bestAskPrice = bestAskPrice;
  }

  public long getBestAskQuantity() {
    return bestAskQuantity;
  }

  public void setBestAskQuantity(long bestAskQuantity) {
    this.bestAskQuantity = bestAskQuantity;
  }

  public double getOpenPrice() {
    return openPrice;
  }

  public void setOpenPrice(double openPrice) {
    this.openPrice = openPrice;
  }

  public double getHighPrice() {
    return highPrice;
  }

  public void setHighPrice(double highPrice) {
    this.highPrice = highPrice;
  }

  public double getLowPrice() {
    return lowPrice;
  }

  public void setLowPrice(double lowPrice) {
    this.lowPrice = lowPrice;
  }

  public long getTotalTradedBaseAssetVolume() {
    return totalTradedBaseAssetVolume;
  }

  public void setTotalTradedBaseAssetVolume(long totalTradedBaseAssetVolume) {
    this.totalTradedBaseAssetVolume = totalTradedBaseAssetVolume;
  }

  public long getTotalTradedQuoteAssetVolume() {
    return totalTradedQuoteAssetVolume;
  }

  public void setTotalTradedQuoteAssetVolume(long totalTradedQuoteAssetVolume) {
    this.totalTradedQuoteAssetVolume = totalTradedQuoteAssetVolume;
  }

  public long getStatisticesOpenTime() {
    return statisticesOpenTime;
  }

  public void setStatisticesOpenTime(long statisticesOpenTime) {
    this.statisticesOpenTime = statisticesOpenTime;
  }

  public long getStatisticesCloseTime() {
    return statisticesCloseTime;
  }

  public void setStatisticesCloseTime(long statisticesCloseTime) {
    this.statisticesCloseTime = statisticesCloseTime;
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
    return "AllMarketTickersEvent [eventType=" + eventType + ", eventTime=" + eventTime
        + ", symbol=" + symbol + ", priceChange=" + priceChange + ", priceChangePercent="
        + priceChangePercent + ", weightedAveragePrice=" + weightedAveragePrice
        + ", previousDaysClosePrice=" + previousDaysClosePrice + ", currentDaysClosePrice="
        + currentDaysClosePrice + ", closeTradesQuantity=" + closeTradesQuantity
        + ", bestAskPrice=" + bestAskPrice + ", bestAskQuantity=" + bestAskQuantity
        + ", openPrice=" + openPrice + ", highPrice=" + highPrice + ", lowPrice=" + lowPrice
        + ", totalTradedBaseAssetVolume=" + totalTradedBaseAssetVolume
        + ", totalTradedQuoteAssetVolume=" + totalTradedQuoteAssetVolume + ", statisticesOpenTime="
        + statisticesOpenTime + ", statisticesCloseTime=" + statisticesCloseTime
        + ", firstTradeId=" + firstTradeId + ", lastTradeId=" + lastTradeId
        + ", totalNumberOfTrades=" + totalNumberOfTrades + "]";
  }
}
