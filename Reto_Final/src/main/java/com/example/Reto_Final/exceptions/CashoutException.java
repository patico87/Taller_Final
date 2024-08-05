package com.example.Reto_Final.exceptions;

public class CashoutException extends RuntimeException{
    public CashoutException(String errorBody) {
        super(errorBody);
    }
}
