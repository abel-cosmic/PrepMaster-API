package com.prepmaster.demo.exception;

//@ResponseStatus // allows us to change the status code
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
