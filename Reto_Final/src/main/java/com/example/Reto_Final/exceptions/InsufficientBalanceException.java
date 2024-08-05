package com.example.Reto_Final.exceptions;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String errorBody) {
        super(errorBody);
    }

}
