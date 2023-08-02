package com.example.demo.dto.mapper;

import com.example.demo.dto.request.StudentRequest;
import com.example.demo.dto.response.StudentResponse;
import com.example.demo.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper MAPPER = Mappers.getMapper(StudentMapper.class);
    Student fromReqToModel(StudentRequest studentRequest);
    StudentResponse fromModelToResponse(Student student);

}
