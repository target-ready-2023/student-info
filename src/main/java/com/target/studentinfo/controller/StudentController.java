package com.target.studentinfo.controller;
import com.target.studentinfo.dto.mapper.StudentMapper;
import com.target.studentinfo.dto.request.StudentRequest;
import com.target.studentinfo.dto.response.StudentResponse;
import com.target.studentinfo.model.Student;
import com.target.studentinfo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    public StudentController(StudentService studentService , StudentMapper studentMapper  ) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @GetMapping("/students")
    public List<StudentResponse> getAllStudentInfo(@RequestParam(defaultValue="true") Boolean isActive){
        List<Student> student = studentService.getAllStudentInfo(isActive);
        return studentMapper.StudentDetails(student);
    }

    @GetMapping("/students/{id}")
    public StudentResponse getStudentById(@PathVariable("id") long id , @RequestParam(defaultValue="true") Boolean isActive){
        Optional<Student> student = studentService.getStudentById(id, isActive);
        System.out.println(student);
        return studentMapper.StudentDetail(student);
    }

    @PostMapping("/students")
    public StudentResponse addStudent(@RequestBody StudentRequest studentRequest){
        Student student = studentMapper.toStudent(studentRequest);
        studentService.addStudent(student);
        return studentMapper.toStudentResponse(student);
    }

    @PutMapping("/students/{id}")
    public StudentResponse updateStudent(@RequestBody StudentRequest studentRequest, @PathVariable("id") long id) {
        Student student = studentMapper.toStudent(studentRequest);
        student.setId(id);
        studentService.updateStudent(student);
        return studentMapper.toStudentResponse(student);
    }

    @DeleteMapping("/students/{id}")
    public void softDelete(@PathVariable("id") long id) {
        studentService.softDelete(id);
    }
   }
