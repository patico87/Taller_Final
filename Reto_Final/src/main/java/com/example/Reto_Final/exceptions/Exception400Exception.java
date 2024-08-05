package com.example.Reto_Final.exceptions;

public class Exception400Exception extends RuntimeException{
    public Exception400Exception(String errorBody) {
        super(errorBody);
    }
}
