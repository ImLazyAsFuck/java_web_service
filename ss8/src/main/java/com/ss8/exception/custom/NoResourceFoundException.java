package com.ss8.exception.custom;

public class NoResourceFoundException extends RuntimeException {
    public NoResourceFoundException(String message) {
        super(message);
    }
}
