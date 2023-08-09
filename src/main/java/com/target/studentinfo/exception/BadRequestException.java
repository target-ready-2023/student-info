package com.target.studentinfo.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException {
    private String errorCode;

    public BadRequestException(String message, HttpStatus errorCode) {
        super(message);
        this.errorCode = String.valueOf(errorCode);
    }

    public String getErrorCode() {
        return errorCode;
    }

}
