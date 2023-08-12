package com.target.studentinfo.model;

import com.target.studentinfo.exception.BadRequestException;
import com.target.studentinfo.exception.ErrorCode;
import com.target.studentinfo.model.Student;

public class StudentValidator {
    public static void validateStudent(Student student) {
        if (student == null) {
            throw new BadRequestException(ErrorCode.INVALID_REQUEST_BODY, "request body cannot be null");
        }

        if (student.getFirstName() == null) {
            throw new BadRequestException(ErrorCode.INVALID_FIRST_NAME, "first name cannot be null");
        }

        if (student.getLastName() == null) {
            throw new BadRequestException(ErrorCode.INVALID_LAST_NAME, "last name cannot be null");
        }

        if (student.getStandard() < 1 || student.getStandard() > 10) {
            throw new BadRequestException(ErrorCode.INVALID_STANDARD, "standard must be between 1 and 10");
        }

    }
}
