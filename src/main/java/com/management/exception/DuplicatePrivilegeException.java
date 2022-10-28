package com.management.exception;

public class DuplicatePrivilegeException extends RuntimeException{

    public DuplicatePrivilegeException(String message) {
        super(String.format("%s privilege exists", message));
    }
}
