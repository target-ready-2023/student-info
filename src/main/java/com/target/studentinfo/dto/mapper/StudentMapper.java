package com.target.studentinfo.dto.mapper;

import com.target.studentinfo.dto.request.StudentRequest;
import com.target.studentinfo.dto.response.StudentResponse;
import com.target.studentinfo.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Builder
@Mapper(componentModel = "spring")
public class StudentMapper implements Serializable {
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

    public static List<StudentResponse> StudentDetails(List<Student> students) {
        ArrayList<StudentResponse> details = new ArrayList<StudentResponse>();
        for (Student student : students) {
            details.add(
                    StudentResponse.builder()
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
                            .build()
            );
        }
        return details;
    }

    public static StudentResponse StudentDetail(Optional<Student> students) {
        {
                if(students.isPresent()) {
                    Student student = students.get();
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
             else{
                 return null;
            }
        }

    }
}
