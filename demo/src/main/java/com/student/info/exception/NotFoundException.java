package com.student.info.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {
    private int errorCode;
    private HttpStatus httpStatus;

    public NotFoundException(String message, int errorCode, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
