package com.target.studentinfo.dto.mapper;

import com.target.studentinfo.dto.request.StudentRequest;
import com.target.studentinfo.dto.response.StudentResponse;
import com.target.studentinfo.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Builder
@Mapper(componentModel = "spring")
@Component
public class StudentMapper {
    public Student toStudent(StudentRequest studentRequest) {
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

    public StudentResponse toStudentResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
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

    public List<StudentResponse> toStudentResponseList(List<Student> students) {
        ArrayList<StudentResponse> studentResponses = new ArrayList<StudentResponse>();
        for (Student student : students) {
            StudentResponse studentResponse = toStudentResponse(student);
            studentResponses.add(studentResponse);
        }
        return studentResponses;
    }

    public StudentResponse toStudentResponse(Optional<Student> students) {
        {
            return students.isPresent() ? toStudentResponse(students.get()) : null;
        }

    }
}
