package com.lifeassist.exception;

@SuppressWarnings("serial")
public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
