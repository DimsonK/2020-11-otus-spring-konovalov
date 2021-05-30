package ru.otus.spring.homework.springproject.exceptions;

public class FileStorageException extends RuntimeException {
    public FileStorageException() {
        super();
    }

    public FileStorageException(final String message) {
        super(message);
    }

    public FileStorageException(final String message, Throwable cause) {
        super(message, cause);
    }
}
