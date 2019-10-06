package com.worldpay.ws.offers.api.error.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@ToString
public class DuplicateTitleException extends RuntimeException {

    private String title;

    public DuplicateTitleException(String title, String message) {
        super(title + " " + message);
        this.title = title;
    }
}