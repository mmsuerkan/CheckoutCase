package com.project.exception;

public class ItemQuantityExceededException extends RuntimeException {
    public ItemQuantityExceededException(String message) {
        super(message);
    }
}
