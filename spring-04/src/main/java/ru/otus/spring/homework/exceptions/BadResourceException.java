package ru.otus.spring.homework.exceptions;

public class BadResourceException extends RuntimeException {
    public BadResourceException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
