package com.snackhub.exception;


public class DomainException extends RuntimeException {

    public DomainException(final String aMessage, final Exception e) {
        super(aMessage + e.getMessage());
    }
}