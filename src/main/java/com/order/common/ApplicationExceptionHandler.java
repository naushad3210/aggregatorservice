package com.order.common;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aggregator.constant.AggregatorServiceErrorCodes;
import com.aggregator.dto.BaseResponse;
import com.order.exception.AggregatorException;

import lombok.extern.slf4j.Slf4j;

/**
 * This class handles the application level exception, if left unhandled.
 * 
 * @ExceptionHandler and provide it with a exception type that we want to
 *                   handle.
 */
@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler {

	private static final String ERROR_RESPONSE_LOG_MESSAGE = "Response | status: {} | code: {} | message: {}";

	@ExceptionHandler(AggregatorException.class)
	public ResponseEntity<BaseResponse> handleGameReportingException(AggregatorException ex) {
		log.error("AggregatorException ", ex);
		return createResponseEntity(ex);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseResponse> handleException(Exception ex) {
		log.error("Exception ", ex);
		return createResponseEntity(new AggregatorException(
				Optional.of(AggregatorServiceErrorCodes.INTERNAL_SERVER_ERROR.getHttpCode()),
				AggregatorServiceErrorCodes.NO_CONTENT.getErrorCode(), AggregatorServiceErrorCodes.NO_CONTENT.getErrorMessage()));
	}

	private ResponseEntity<BaseResponse> createResponseEntity(AggregatorException ex) {
		Integer httpCode = ex.getHttpCode().orElse(HttpStatus.INTERNAL_SERVER_ERROR.value());
		log.error(ERROR_RESPONSE_LOG_MESSAGE, httpCode, ex.getErrorCode(), ex.getMessage());
		return ResponseEntity.status(httpCode).body(new BaseResponse(ex.getErrorCode(), ex.getMessage()));
	}

}
