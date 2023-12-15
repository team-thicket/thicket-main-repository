package com.example.thicketmember.exception;

public class ExpiredVerificationCodeException extends RuntimeException{
    public ExpiredVerificationCodeException(String message) {
        super(message);
    }
}
