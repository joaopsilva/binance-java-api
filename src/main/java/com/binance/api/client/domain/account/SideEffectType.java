package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Desired side-effect for margin orders:
 * NO_SIDE_EFFECT for normal trade order;
 * MARGIN_BUY for margin trade order;
 * AUTO_REPAY for making auto repayment after order filled
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum SideEffectType {
    NO_SIDE_EFFECT,
    MARGIN_BUY,
    AUTO_REPAY
}

