package com.target.studentinfo.controller;
import com.target.studentinfo.dto.mapper.StudentMapperNew;
import com.target.studentinfo.dto.request.StudentRequest;
import com.target.studentinfo.dto.response.StudentResponse;
import com.target.studentinfo.model.Student;
import com.target.studentinfo.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudentInfo(){

        return studentService.getAllStudentInfo();
    }

    @GetMapping("/students/{id}")
    public Optional<Student> getStudentById(@PathVariable("id") UUID id){
        return studentService.getStudentById(id);
    }

    @PostMapping("/students")
    public Student addNewStudent(@RequestBody Student student){
        return studentService.addNewStudent(student);
    }

    @PutMapping("/students/{id}")
    public StudentResponse updateStudent(@RequestBody StudentRequest studentRequest, @PathVariable("id") UUID id) {
        Optional<Student> checkExists = getStudentById(id);
        if (! checkExists.isPresent())
            throw new RuntimeException("Student ID" + id + " not found");
        Student student = StudentMapperNew.toStudent(studentRequest);
        student.setId(id);
        studentService.updateStudent(student);
        return StudentMapperNew.toStudentResponse(student);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") UUID id) {
        studentService.deleteStudent(id);
    }

   }
