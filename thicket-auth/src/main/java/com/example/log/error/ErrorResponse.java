package com.example.log.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final int status;
    private final ErrorCode errorCode;

    public static ErrorResponse of(int status, ErrorCode errorCode) {
        return new ErrorResponse(status, errorCode);
    }
}