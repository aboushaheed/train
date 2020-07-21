package com.train.company.interfaces.console.in.exception;

public class ReadingFileException extends RuntimeException {
    private String message;
    public ReadingFileException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
