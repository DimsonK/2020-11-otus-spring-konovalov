package ru.otus.spring.homework.springproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super();
    }

    public ForbiddenException(final String message) {
        super(message);
    }

    public ForbiddenException(final String message, Throwable cause) {
        super(message, cause);
    }
}
