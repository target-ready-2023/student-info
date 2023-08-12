package com.target.studentinfo.exception;

public final class ErrorMessage {

    private ErrorMessage() {
    }

    public static final String INVALID_REQUEST_BODY = "request body cannot be null";
    public static final String INVALID_FIRST_NAME = "first name cannot be null";
    public static final String INVALID_LAST_NAME = "last name cannot be null";
    public static final String INVALID_STANDARD = "standard must be between 1 and 10";
    public static final String STUDENT_NOT_FOUND = "student id not found";
}
