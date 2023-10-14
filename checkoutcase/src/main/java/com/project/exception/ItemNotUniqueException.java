package com.project.exception;

public class ItemNotUniqueException extends RuntimeException {
    public ItemNotUniqueException(String message) {
        super(message);
    }
}
