package com.target.studentinfo.dto.validator;

import com.target.studentinfo.dto.request.StudentRequest;
import com.target.studentinfo.exception.BadRequestException;
import com.target.studentinfo.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class StudentValidator {
    public static void validateStudentRequest(StudentRequest studentRequest) {
        if (studentRequest == null) {
            throw new BadRequestException(ErrorCode.INVALID_REQUEST_BODY, HttpStatus.BAD_REQUEST);
        }

        if (studentRequest.getFirstName() == null) {
            throw new BadRequestException(ErrorCode.INVALID_FIRST_NAME, HttpStatus.BAD_REQUEST);
        }

        if (studentRequest.getLastName() == null) {
            throw new BadRequestException(ErrorCode.INVALID_LAST_NAME, HttpStatus.BAD_REQUEST);
        }

        if (studentRequest.getEmailId() == null) {
            throw new BadRequestException(ErrorCode.INVALID_EMAIL_ID, HttpStatus.BAD_REQUEST);
        }
    }
}
