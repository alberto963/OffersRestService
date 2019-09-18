package com.worldpay.ws.offers.api.error.message;

public class ExceptionMessage {

    public static final String EXCEPTION_MESSAGE_DUPLICATE_OFFER_ID = "offerID already in use";
    public static final String EXCEPTION_MESSAGE_OFFER_NOT_FOUND = "offer not found";
    public static final String EXCEPTION_MESSAGE_FOREIGN_KEY_VIOLATION = "comment refers to a non existing offer";
}
