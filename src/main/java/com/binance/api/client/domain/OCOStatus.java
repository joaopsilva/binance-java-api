package com.binance.api.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum OCOStatus {
    RESPONSE,
    EXEC_STARTED,
    ALL_DONE
}