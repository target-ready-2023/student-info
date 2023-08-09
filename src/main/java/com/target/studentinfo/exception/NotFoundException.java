package com.target.studentinfo.exception;


public class NotFoundException extends RuntimeException {
    private int errorCode;

    public NotFoundException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }


}
