package com.target.studentinfo.dto.mapper;

import com.target.studentinfo.dto.request.StudentRequest;
import com.target.studentinfo.dto.response.StudentResponse;
import com.target.studentinfo.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper MAPPER = Mappers.getMapper(StudentMapper.class);
    Student fromReqToModel(StudentRequest studentRequest);
    StudentResponse fromModelToResponse(Student student);

}
