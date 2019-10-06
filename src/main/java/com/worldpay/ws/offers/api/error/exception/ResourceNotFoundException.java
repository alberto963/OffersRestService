package com.worldpay.ws.offers.api.error.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@ToString
public class ResourceNotFoundException extends RuntimeException {

    private long resourceId;
    private String title;

    public ResourceNotFoundException(long resourceId, String message) {
        super(resourceId + " " + message);
        this.resourceId = resourceId;
    }

	public ResourceNotFoundException(String title, String message) {
        super(title + " " + message);
        this.title = title;
	}
}