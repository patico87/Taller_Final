package com.example.Reto_Final.exceptions;

public class PaymentException extends RuntimeException{
    public PaymentException(String errorBody) {
        super(errorBody);
    }
}
