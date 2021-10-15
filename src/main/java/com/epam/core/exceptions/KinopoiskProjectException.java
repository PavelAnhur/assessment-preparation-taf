package com.epam.core.exceptions;

public class KinopoiskProjectException extends RuntimeException {

    public KinopoiskProjectException(final String message) {
        super(message);
    }

    public KinopoiskProjectException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
