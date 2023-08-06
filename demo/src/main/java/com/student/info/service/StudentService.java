package com.student.info.service;

import com.student.info.dto.mapper.StudentMapper;
import com.student.info.dto.request.StudentRequest;
import com.student.info.dto.response.StudentResponse;
import com.student.info.exception.RequestValidationException;
import com.student.info.model.Student;
import com.student.info.respository.StudentRepository;
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
            throw new RequestValidationException("Student request body cannot be null.");
        }

        // separate out the fields
        if (studentRequest.getFirstName() == null || studentRequest.getLastName() == null || studentRequest.getEmailId() == null) {
            throw new RequestValidationException("Student request body contains null parameters.");
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





