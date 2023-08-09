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
@RequestMapping("/student_information_service/v1")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudentInfo(@RequestParam(defaultValue="false") Boolean isAdmin){

        if(isAdmin==true)
        {
            return studentService.getAllStudentInfo();
        }
        else
        {
            return studentService.findActive();
        }
    }

    @GetMapping("/student/{id}")
    public Optional<Student> getStudentById(@PathVariable("id") UUID id){
        return studentService.getStudentById(id);
    }

//    @GetMapping("/students/archives")
//    public List<Student> findArchived() {
//        return studentService.findArchived();
//    }


    @PostMapping("/student")
    public Student addNewStudent(@RequestBody Student student){
        return studentService.addNewStudent(student);
    }

    @PutMapping("/student/{id}")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }


//    @DeleteMapping("/student/{id}")
//    public void deleteStudent(@PathVariable("id") UUID id) {
//        studentService.deleteStudent(id);
//    }

    @DeleteMapping("/students/{id}")
    public void softDelete(@PathVariable("id") UUID id) {
        studentService.archive(id);
    }

    @PostMapping("/student/resp")
    public StudentResponse addNewStudentResponse(@RequestBody StudentRequest studentRequest) {
        return studentService.addNewStudent(studentRequest);
    }

    @PutMapping("/student/{id}/resp")
    public StudentResponse updateStudentResponse(@RequestBody StudentRequest studentRequest, @PathVariable("id") UUID id) {
        return studentService.updateStudent(studentRequest,id);
    }

   }
