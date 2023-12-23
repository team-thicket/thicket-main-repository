package com.example.thicketstage.exception;

public class ServerConnectionOutException extends RuntimeException{
    public ServerConnectionOutException(String message) {
        super(message);
    }
}
