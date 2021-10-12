package com.anyvision.test.restapianyvision.exception;

public class LimitNumOfPersonsException extends RuntimeException {
    public LimitNumOfPersonsException(String message, Throwable cause) {
        super(message, cause);
    }

    public LimitNumOfPersonsException(String message) {
        super(message);
    }
}
