package com.student.info.exception;

public class ErrorCode {
    // Error codes
    public static final int NOT_FOUND = 404;
    public static final int BAD_REQUEST = 400;
    public static final int INTERNAL_SERVER_ERROR = 500;

    // Error messages
    public static final String INVALID_REQUEST_BODY = "Request body cannot be null";
    public static final String INVALID_FIRST_NAME = "First name cannot be null";
    public static final String INVALID_LAST_NAME = "Last name cannot be null";
    public static final String INVALID_EMAIL_ID = "Email id cannot be null";
}
