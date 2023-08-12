package com.target.studentinfo.service;

import com.target.studentinfo.dto.mapper.StudentMapper;
import com.target.studentinfo.dto.request.StudentRequest;
import com.target.studentinfo.dto.response.StudentResponse;
import com.target.studentinfo.model.Student;
import com.target.studentinfo.respository.StudentRepository;
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

    public List<Student> getAllStudentInfo(Boolean isActive){

        return (isActive ? studentRepository.findActive() : studentRepository.findAll());
    }

    public Optional<Student> getStudentById(UUID id, Boolean isActive){

        return (isActive ? studentRepository.findActiveID(id) : studentRepository.findById(id));
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

}





