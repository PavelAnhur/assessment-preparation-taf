package com.epam.core.exceptions;

public class CustomProjectException extends RuntimeException {

    public CustomProjectException(String message) {
        super(message);
    }

    public CustomProjectException(String message, Throwable cause) {
        super(message, cause);
    }
}
