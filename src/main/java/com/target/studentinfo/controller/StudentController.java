package com.target.studentinfo.controller;
import com.target.studentinfo.dto.mapper.StudentMapper;
import com.target.studentinfo.dto.request.StudentRequest;
import com.target.studentinfo.dto.response.StudentResponse;
import com.target.studentinfo.model.Student;
import com.target.studentinfo.service.StudentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    private final StudentService studentService;
    //private final StudentMapper studentMapper;

    public StudentController(StudentService studentService  ) {
        this.studentService = studentService;
        //this.studentMapper = studentMapper;
    }

    @GetMapping("/students")
    public List<StudentResponse> getAllStudentInfo(){
        List<Student> student = studentService.getAllStudentInfo();
        return  StudentMapper.StudentDetails(student);
    }

    @GetMapping("/students/{id}")
    public StudentResponse getStudentById(@PathVariable("id") long id){
        Optional<Student> student = studentService.getStudentById(id);
        return StudentMapper.StudentDetail(student);
    }

    @PostMapping("/students")
    public StudentResponse addStudent(@RequestBody StudentRequest studentRequest){
        Student student = StudentMapper.toStudent(studentRequest);
        studentService.addStudent(student);
        return StudentMapper.toStudentResponse(student);
    }

    @PutMapping("/students/{id}")
    public StudentResponse updateStudent(@RequestBody StudentRequest studentRequest, @PathVariable("id") long id) {
        Student student = StudentMapper.toStudent(studentRequest);
        student.setId(id);
        studentService.updateStudent(student);
        return StudentMapper.toStudentResponse(student);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") long id) {
        studentService.deleteStudent(id);
    }
   }
