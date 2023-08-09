package com.student.info.service;

import com.student.info.dto.mapper.StudentMapper;
import com.student.info.dto.request.StudentRequest;
import com.student.info.dto.response.StudentResponse;
import com.student.info.exception.ErrorCode;
import com.student.info.exception.BadRequestException;
import com.student.info.model.Student;
import com.student.info.respository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudentInfo(){

        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(UUID id){
        return studentRepository.findById(id);
    }

    public Student addNewStudent(Student student){
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    };

    public void deleteStudent(UUID id) {
        studentRepository.deleteById(id);
    };

    public StudentResponse addNewStudent(StudentRequest studentRequest) {

        if (studentRequest == null) {
            throw new BadRequestException(ErrorCode.INVALID_REQUEST_BODY, ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
        }

        if (studentRequest.getFirstName() == null) {
            throw new BadRequestException(ErrorCode.INVALID_FIRST_NAME, ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
        }

        if (studentRequest.getLastName() == null) {
            throw new BadRequestException(ErrorCode.INVALID_LAST_NAME, ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
        }

        if (studentRequest.getEmailId() == null) {
            throw new BadRequestException(ErrorCode.INVALID_EMAIL_ID, ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
        }

        Student student = StudentMapper.MAPPER.fromReqToModel(studentRequest);
        studentRepository.save(student);
        return StudentMapper.MAPPER.fromModelToResponse(student);
    }



    ;
    public StudentResponse updateStudent(StudentRequest studentRequest,UUID id) {

        Optional<Student> checkExists = getStudentById(id);
        if (! checkExists.isPresent())
            throw new RuntimeException("Employee ID" + id + " not found");
        Student student = StudentMapper.MAPPER.fromReqToModel(studentRequest);
        student.setId(id);
        studentRepository.save(student);
        return StudentMapper.MAPPER.fromModelToResponse(student);
    };






}





