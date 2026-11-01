package com.pvmplanner.backend.exception;

public class DuplicateProgressException extends RuntimeException {

    public DuplicateProgressException(String message) {
        super(message);
    }
}