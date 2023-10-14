package com.project.exception;

public class TotalAmountExceededException extends RuntimeException {
    public TotalAmountExceededException(String message) {
        super(message);
    }
}
