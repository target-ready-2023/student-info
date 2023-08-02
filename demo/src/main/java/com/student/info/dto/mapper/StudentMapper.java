package com.student.info.dto.mapper;

import com.student.info.dto.request.StudentRequest;
import com.student.info.dto.response.StudentResponse;
import com.student.info.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper MAPPER = Mappers.getMapper(StudentMapper.class);
    Student fromReqToModel(StudentRequest studentRequest);
    StudentResponse fromModelToResponse(Student student);

}
