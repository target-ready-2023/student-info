package com.target.studentinfo.exception;

import org.springframework.http.HttpStatus;

public class InternalErrorException extends RuntimeException {
    private final ErrorCode errorCode;

    public InternalErrorException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
