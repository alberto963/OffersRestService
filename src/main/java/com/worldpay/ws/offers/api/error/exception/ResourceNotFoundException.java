package com.worldpay.ws.offers.api.error.exception;

public class ResourceNotFoundException extends RuntimeException {

    private long resourceId;

    public ResourceNotFoundException(long resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }
}