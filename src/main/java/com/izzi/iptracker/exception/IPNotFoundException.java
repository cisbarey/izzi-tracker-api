package com.izzi.iptracker.exception;

public class IPNotFoundException extends RuntimeException {
    public IPNotFoundException(String message) {
        super(message);
    }
}
