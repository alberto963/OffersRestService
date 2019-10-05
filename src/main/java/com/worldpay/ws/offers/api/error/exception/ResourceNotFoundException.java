package com.worldpay.ws.offers.api.error.exception;

public class ResourceNotFoundException extends RuntimeException {

    private long resourceId;
    private String title;

    public ResourceNotFoundException(long resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }

	public ResourceNotFoundException(String title, String message) {
        super(message);
	}
}