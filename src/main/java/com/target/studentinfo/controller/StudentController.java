package com.target.studentinfo.controller;

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
    public List<Student> getAllStudentInfo(@RequestParam(defaultValue="true") Boolean isActive){
        return studentService.getAllStudentInfo(isActive);
    }

    @GetMapping("/students/{id}")
    public Optional<Student> getStudentById(@PathVariable("id") UUID id, @RequestParam(defaultValue="true") Boolean isActive){

        return studentService.getStudentById(id, isActive);
    }


    @PostMapping("/students")
    public Student addNewStudent(@RequestBody Student student){
        return studentService.addNewStudent(student);
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }


    @DeleteMapping("/students/{id}")
    public void softDelete(@PathVariable("id") UUID id) {
        studentService.softDelete(id);
    }

   }
