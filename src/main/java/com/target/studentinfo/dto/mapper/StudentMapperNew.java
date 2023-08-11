package com.target.studentinfo.dto.mapper;

import com.target.studentinfo.dto.request.StudentRequest;
import com.target.studentinfo.dto.response.StudentResponse;
import com.target.studentinfo.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class StudentMapperNew {
    public static Student toStudent(StudentRequest studentRequest) {
        return Student.builder()
                .firstName(studentRequest.getFirstName())
                .lastName(studentRequest.getLastName())
                .emailId(studentRequest.getEmailId())
                .bloodGroup(studentRequest.getBloodGroup())
                .fatherName(studentRequest.getFatherName())
                .motherName(studentRequest.getMotherName())
                .age(studentRequest.getAge())
                .gender(studentRequest.getGender())
                .address(studentRequest.getAddress())
                .standard(studentRequest.getStandard())
                .extraCurricular(studentRequest.getExtraCurricular())
                .allergies(studentRequest.getAllergies())
                .transport(studentRequest.getTransport())
                .build();
    }

    public static StudentResponse toStudentResponse(Student student) {
        return StudentResponse.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .emailId(student.getEmailId())
                .bloodGroup(student.getBloodGroup())
                .fatherName(student.getFatherName())
                .motherName(student.getMotherName())
                .age(student.getAge())
                .gender(student.getGender())
                .address(student.getAddress())
                .standard(student.getStandard())
                .extraCurricular(student.getExtraCurricular())
                .allergies(student.getAllergies())
                .transport(student.getTransport())
                .build();
    }
}
