package com.management.exception;

public class WrongDateException extends RuntimeException{

    public WrongDateException() {
        super("Please, enter valid dates");
    }
}
