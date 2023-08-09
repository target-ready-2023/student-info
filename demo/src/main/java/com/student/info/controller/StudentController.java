package com.student.info.controller;

import com.student.info.dto.request.StudentRequest;
import com.student.info.dto.response.StudentResponse;
import com.student.info.model.Student;
import com.student.info.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/student_information_service/v1")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudentInfo(){

        return studentService.getAllStudentInfo();
        //add archived query param
    }

    @GetMapping("/students/{id}")
    public Optional<Student> getStudentById(@PathVariable("id") UUID id){
        return studentService.getStudentById(id);
    }

    @GetMapping("/students/archives")
    public List<Student> findDeleted() {
        return studentService.findDeleted();
    }

    @PostMapping("/students")
    public Student addNewStudent(@RequestBody Student student){
        return studentService.addNewStudent(student);
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }


//    @DeleteMapping("/students/{id}")
//    public void deleteStudent(@PathVariable("id") UUID id) {
//        studentService.deleteStudent(id);
//    }

    @DeleteMapping("/students/{id}")
    public void softDelete(@PathVariable("id") UUID id) {
        studentService.softDelete(id);
    }
    @PostMapping("/students/resp")
    public StudentResponse addNewStudentResponse(@RequestBody StudentRequest studentRequest) {
        return studentService.addNewStudent(studentRequest);
    }

    @PutMapping("/students/{id}/resp")
    public StudentResponse updateStudentResponse(@RequestBody StudentRequest studentRequest, @PathVariable("id") UUID id) {
        return studentService.updateStudent(studentRequest,id);
    }

   }
