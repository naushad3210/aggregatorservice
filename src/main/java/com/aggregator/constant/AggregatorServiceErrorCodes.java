package com.aggregator.constant;

import static com.aggregator.constant.Constants.ERR_MSG_NO_CONTENT;
import static com.aggregator.constant.Constants.ERR_MSG_TECHNICAL_ERROR;

import lombok.Getter;

@Getter
public enum AggregatorServiceErrorCodes {

    NO_CONTENT(204, 1204, ERR_MSG_NO_CONTENT), 
    INTERNAL_SERVER_ERROR(500, 1500, ERR_MSG_TECHNICAL_ERROR),
    REST_CLIENT_EXCEPTION(500, 1500, ERR_MSG_TECHNICAL_ERROR);
    
    private final int httpCode;
    private final int errorCode;
    private final String errorMessage;

    private AggregatorServiceErrorCodes(int httpCode, int errorCode, String errorMessage) {
        this.httpCode = httpCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
