package com.worldpay.ws.offers.api.error.exception;

public class DuplicateTitleException extends RuntimeException {

    private String title;

    public DuplicateTitleException(String title, String message) {
        super(message);
        this.title = title;
    }
}