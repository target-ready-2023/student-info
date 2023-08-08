package com.student.info.controller;

import com.student.info.dto.request.StudentRequest;
import com.student.info.dto.response.StudentResponse;
import com.student.info.model.Student;
import com.student.info.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/student_information_service/v1")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudentInfo(){

        return studentService.getAllStudentInfo();
    }

    @GetMapping("/student/{id}")
    public Optional<Student> getStudentById(@PathVariable("id") long id){
        return studentService.getStudentById(id);
    }

    @PostMapping("/student")
    public Student addNewStudent(@RequestBody Student student){
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

//    @PostMapping("/student/resp")
//    public StudentResponse addNewStudentResponse(@RequestBody StudentRequest studentRequest) {
//        return studentService.addNewStudent(studentRequest);
//    }

    @PostMapping("/student/resp")
    public ResponseEntity<StudentResponse> addNewStudent(@RequestBody StudentRequest studentRequest) {
        StudentResponse response = studentService.addNewStudent(studentRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/student/{id}/resp")
    public StudentResponse updateStudentResponse(@RequestBody StudentRequest studentRequest, @PathVariable("id") long id) {
        return studentService.updateStudent(studentRequest,id);
    }

   }
