package com.project.exception;

public class CartFullException extends RuntimeException {
    public CartFullException(String message) {
        super(message);
    }
}
