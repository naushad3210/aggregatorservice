package com.order.exception;

import java.util.Optional;

import lombok.Getter;

@Getter
public class AggregatorException extends Exception{

	private static final long serialVersionUID = 571651598861705705L;
	private int errorCode;
    private Optional<Integer> httpCode;

    public AggregatorException(Optional<Integer> httpCode, int errorCode, String message) {
        super(message);
        this.httpCode = httpCode;
        this.errorCode = errorCode;
    }
}
