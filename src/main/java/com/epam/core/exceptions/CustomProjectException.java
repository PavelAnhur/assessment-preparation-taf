package com.epam.core.exceptions;

public class CustomProjectException extends RuntimeException {

    public CustomProjectException(final String message) {
        super(message);
    }

    public CustomProjectException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
