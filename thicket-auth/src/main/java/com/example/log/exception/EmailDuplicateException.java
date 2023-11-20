package com.example.log.exception;

public class EmailDuplicateException extends RuntimeException {
    public EmailDuplicateException(String message) {
        super("이미 존재하는 아이디 입니다.");
    }
}