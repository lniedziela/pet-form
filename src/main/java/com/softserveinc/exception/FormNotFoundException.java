package com.softserveinc.exception;

public class FormNotFoundException extends RuntimeException {
    public FormNotFoundException(String message) {
        super(message);
    }
}
