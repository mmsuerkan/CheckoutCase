package com.project.exception;

public class TotalQuantityExceededException extends RuntimeException {
    public TotalQuantityExceededException(String message) {
        super(message);
    }
}
