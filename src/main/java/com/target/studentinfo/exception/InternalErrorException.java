package com.target.studentinfo.exception;

import org.springframework.http.HttpStatus;

public class InternalErrorException extends RuntimeException {
    private String errorCode;

    public InternalErrorException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
