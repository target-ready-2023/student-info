package com.target.studentinfo.exception;

import org.springframework.http.HttpStatus;

public class InternalErrorException extends RuntimeException {
    private int errorCode;
    private HttpStatus httpStatus;

    public InternalErrorException(String message, int errorCode, HttpStatus httpStatus) {
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
