package com.izzi.iptracker.exception;

public class IPAlreadyExistsException extends RuntimeException {
    public IPAlreadyExistsException(String message) {
        super(message);
    }
}
