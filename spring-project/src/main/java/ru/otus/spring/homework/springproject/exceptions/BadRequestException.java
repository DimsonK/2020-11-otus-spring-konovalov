package ru.otus.spring.homework.springproject.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super();
    }

    public BadRequestException(final String message) {
        super(message);
    }

    public BadRequestException(final String message, Throwable cause) {
        super(message, cause);
    }
}
