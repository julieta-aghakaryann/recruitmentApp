package com.management.exception;

public class WrongEmailException extends RuntimeException{

    public WrongEmailException() {
        super("Please, enter valid email");
    }
}
