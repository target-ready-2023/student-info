package com.example.demo.controller;

import com.example.demo.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student_information_service/v1")
public class StudentController {
    Student student1 = new Student();
    Student student2 = new Student(5768,"Mary","Jane","maryjane@yahoo.com","A+","David","Wendy",13,"Female","Mumbai",7,new String[] {"Physics","History"},"Basketball","Peanuts","Bus");
    List<Student> res = new ArrayList<Student>();
    public StudentController() {

        res.add(student1);
        res.add(student2);
    }

    @GetMapping("/student")
    public List<Student> getAllStudentInfo(){

        return res;
    }

//    @GetMapping("/student/1")
//    public Student getStudentInfo(){
//        return student1;
//    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable(value = "id") long id)
    {
//        Student student3 = res.get(0);
//        int _final=(int)id-1;
//        Student student4 = res.get(_final);
        for(int i=0;i<res.toArray().length;i++)
        {
            long var = res.get(i).getId();
            if(var==id)
            {
                return res.get(i);
            }
        }
        return null;
    }
}
