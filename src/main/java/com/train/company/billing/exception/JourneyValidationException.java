package com.train.company.billing.exception;

public class JourneyValidationException extends RuntimeException {

    private String message;

    public JourneyValidationException( String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
