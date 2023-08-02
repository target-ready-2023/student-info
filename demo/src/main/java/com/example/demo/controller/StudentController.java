package com.example.demo.controller;

import com.example.demo.dto.request.StudentRequest;
import com.example.demo.dto.response.StudentResponse;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student_information_service/v1")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudentInfo() {

        return studentService.getAllStudentInfo();
    }

    @GetMapping("/student/{id}")
    public Optional<Student> getStudentById(@PathVariable("id") long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/student")
    public Student addNewStudent(@RequestBody Student student) {
        return studentService.addNewStudent(student);
    }

    @PutMapping("/student/{id}")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }


    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable("id") long id) {
        studentService.deleteStudent(id);
    }

    @PostMapping("/student/resp")
    public StudentResponse addNewStudentResponse(@RequestBody StudentRequest studentRequest) {
        return studentService.addNewStudent(studentRequest);
    }

    @PutMapping("/student/{id}/resp")
    public StudentResponse updateStudentResponse(@RequestBody StudentRequest studentRequest, @PathVariable("id") long id) {
        return studentService.updateStudent(studentRequest, id);
    }

}
