package com.management.exception;

public class DuplicatePositionException extends RuntimeException{

    public DuplicatePositionException(String message) {
        super(String.format("%s position exists", message));
    }
}
