package com.student.info.service;

import com.student.info.dto.mapper.StudentMapper;
import com.student.info.dto.request.StudentRequest;
import com.student.info.dto.response.StudentResponse;
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

    public Optional<Student> getStudentById(long id){
        return studentRepository.findById(id);
    }

    public List<Student> findDeleted() {
        return studentRepository.findDeleted();
    }

    public void softDelete(UUID id) {
        studentRepository.softDelete(id);
    }
    public Student addNewStudent(Student student){
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    };

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    };

    public StudentResponse addNewStudent(StudentRequest studentRequest) {

        Student student = StudentMapper.MAPPER.fromReqToModel(studentRequest);
        studentRepository.save(student);
        return StudentMapper.MAPPER.fromModelToResponse(student);
    };
    public StudentResponse updateStudent(StudentRequest studentRequest,long id) {

        Optional<Student> checkExists = getStudentById(id);
        if (! checkExists.isPresent())
            throw new RuntimeException("Employee ID" + id + " not found");
        Student student = StudentMapper.MAPPER.fromReqToModel(studentRequest);
        student.setId(id);
        studentRepository.save(student);
        return StudentMapper.MAPPER.fromModelToResponse(student);
    };






}





