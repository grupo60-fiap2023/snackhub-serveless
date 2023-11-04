package com.snackhub.exception;


public class AWSException extends RuntimeException {

    public AWSException(final String aMessage, final Exception e) {
        super(aMessage + e.getMessage());
    }
}