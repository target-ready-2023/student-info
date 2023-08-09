package com.target.studentinfo.exception;

import org.springframework.http.HttpStatus;

public class InternalErrorException extends RuntimeException {
    private int errorCode;
    private HttpStatus httpStatus;

    public InternalErrorException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
