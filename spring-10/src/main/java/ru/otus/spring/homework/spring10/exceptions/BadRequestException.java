package ru.otus.spring.homework.spring10.exceptions;

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