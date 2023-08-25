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
    private final StudentMapper studentMapper;

    public StudentController(StudentService studentService , StudentMapper studentMapper  ) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @GetMapping("/students/all")
    public List<StudentResponse> getAllStudents(){
        List<Student> allStudents = studentService.getAllStudents();
        return studentMapper.toStudentResponseList(allStudents);
    }

    @GetMapping("/students")
    public List<StudentResponse> getStudents(@RequestParam(defaultValue="true") Boolean isActive, @RequestParam List<Integer> standardList){
        List<Student> students = studentService.getStudents(isActive, standardList);
        return studentMapper.toStudentResponseList(students);
    }

    @GetMapping("/students/{id}")
    public StudentResponse getStudent(@RequestParam(defaultValue="true") Boolean isActive, @PathVariable("id") long id){
        Optional<Student> student = studentService.getStudent(isActive, id);
        return studentMapper.toStudentResponse(student);
    }

    @PostMapping("/students")
    public StudentResponse addStudent(@RequestBody StudentRequest studentRequest){
        Student student = studentMapper.toStudent(studentRequest);
        Student addedStudent = studentService.addStudent(student);
        return studentMapper.toStudentResponse(addedStudent);
    }

    @PutMapping("/students/{id}")
    public StudentResponse updateStudent(@RequestBody StudentRequest studentRequest, @PathVariable("id") long id) {
        Student student = studentMapper.toStudent(studentRequest);
        Student updatedStudent = studentService.updateStudent(student,id);
        return studentMapper.toStudentResponse(updatedStudent);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") long id) {
        studentService.deleteStudent(id);
    }
   }
