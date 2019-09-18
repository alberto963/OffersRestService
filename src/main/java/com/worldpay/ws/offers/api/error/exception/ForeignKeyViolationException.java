package com.worldpay.ws.offers.api.error.exception;

public class ForeignKeyViolationException extends RuntimeException {

    private long offerId;

    public ForeignKeyViolationException(long offerId, String message) {
        super(message);
        this.offerId = offerId;
    }
}