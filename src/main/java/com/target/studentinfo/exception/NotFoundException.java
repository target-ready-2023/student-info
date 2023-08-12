package com.target.studentinfo.exception;


public class NotFoundException extends RuntimeException {
    private String errorCode;

    public NotFoundException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }


}
