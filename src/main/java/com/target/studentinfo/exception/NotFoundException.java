package com.target.studentinfo.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {
    private int errorCode;
    private HttpStatus httpStatus;

    public NotFoundException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }


}
