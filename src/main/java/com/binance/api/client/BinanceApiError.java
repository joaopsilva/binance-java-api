package com.binance.api.client;

/**
 * Binance API error object.
 */
public class BinanceApiError {

  private int code;

  private String msg;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BinanceApiError{");
    sb.append("code=").append(code);
    sb.append(", msg='").append(msg).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
