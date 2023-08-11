package com.target.studentinfo.service;
import com.target.studentinfo.model.Student;
import com.target.studentinfo.respository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Student addStudent(Student student){
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    };

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    };

}





