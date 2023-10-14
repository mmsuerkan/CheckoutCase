package com.project.exception;

public class VasItemPriceExceededException extends RuntimeException {
    public VasItemPriceExceededException(String message) {
        super(message);
    }
}
