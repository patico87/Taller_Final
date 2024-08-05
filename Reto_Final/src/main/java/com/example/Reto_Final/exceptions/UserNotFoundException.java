package com.example.Reto_Final.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String errorBody) {
        super(errorBody);
    }
}
