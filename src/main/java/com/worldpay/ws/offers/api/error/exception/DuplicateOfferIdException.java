package com.worldpay.ws.offers.api.error.exception;

public class DuplicateOfferIdException extends RuntimeException {

    private long offerId;

    public DuplicateOfferIdException(long offerId, String message) {
        super(message);
        this.offerId = offerId;
    }
}