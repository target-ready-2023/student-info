package com.example.demo.service;

import com.example.demo.dto.mapper.StudentMapper;
import com.example.demo.dto.request.StudentRequest;
import com.example.demo.dto.response.StudentResponse;
import com.example.demo.model.Student;
import com.example.demo.respository.StudentRepository;
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

        Student student = StudentMapper.MAPPER.fromReqToModel(studentRequest);
        studentRepository.save(student);
        return StudentMapper.MAPPER.fromModelToResponse(student);
    };
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





