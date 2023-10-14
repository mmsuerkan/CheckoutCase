package com.project.exception;

public class VasItemLimitExceededException extends RuntimeException {

    public VasItemLimitExceededException(String message) {
        super(message);
    }
}
