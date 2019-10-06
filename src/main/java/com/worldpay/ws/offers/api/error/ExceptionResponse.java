package com.worldpay.ws.offers.api.error;

import lombok.Data;

@Data
public class ExceptionResponse {
    private String errorCode;
    private String errorMessage;
}