package com.worldpay.ws.offers.api.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.worldpay.ws.offers.api.error.ExceptionResponse;

public abstract class AbstractGlobalExceptionHandler {

	public ResponseEntity<ExceptionResponse> buildAndSendErrorResponse(Exception ex, String errorCode,
			HttpStatus httpStatus) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode(errorCode);
		response.setErrorMessage(ex.getMessage());

		return new ResponseEntity<>(response, httpStatus);
	}
}
