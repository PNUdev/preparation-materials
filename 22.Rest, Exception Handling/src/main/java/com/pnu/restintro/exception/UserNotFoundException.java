package com.pnu.restintro.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

}
