package com.target.studentinfo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.studentinfo.dto.request.StudentRequest;
import com.target.studentinfo.exception.BadRequestException;
import com.target.studentinfo.exception.ErrorCode;
import com.target.studentinfo.exception.ErrorMessage;
import com.target.studentinfo.model.Student;
import com.target.studentinfo.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
@ComponentScan(basePackages = "com.target.studentinfo")
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;
    Student student1,student2,student3;
    private List<Student> mockStudents=new ArrayList<>();
    @BeforeEach
    void setup(){
        student1=new Student(1L, "Ram", "K", "ram@123.com", "A+", "Ashok", "Swathi", 12, "male", "hyderabad", 8, "painting", "none", "self",true );
        student2=new Student(2L, "John", "Deo", "john@123.com", "B+", "Smith", "Jane", 14, "male", "kerala", 10, "music", "dust", "self", true);
        student3=new Student(3L, "Raksh", "K", "raksh@123.com", "A+", "Ashok", "Swathi", 10, "male", "hyderabad", 6, "painting", "none", "self", false);
        mockStudents.add(student1);
        mockStudents.add(student2);
        mockStudents.add(student3);
    }
    @Test
    public void givenStudentId_whenGetStudentById_thenReturnStudentResponse() throws Exception {
        when(studentService.getStudent(student1.getId(), true)).thenReturn(Optional.of(student1));

        mockMvc.perform(MockMvcRequestBuilders.get("/students/{id}", student1.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(student1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(student1.getFirstName()));

        verify(studentService).getStudent(student1.getId(), true);
    }
    @Test
    public void givenListOfStudents_whenGetAllStudents_thenReturnStudentsList() throws Exception {
        List<Student> activeStudents=new ArrayList<>();
        activeStudents.add(student1);
        activeStudents.add(student2);
        when(studentService.getAllStudents(true)).thenReturn(activeStudents);
        mockMvc.perform(MockMvcRequestBuilders.get("/students"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(activeStudents.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(activeStudents.get(0).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value(activeStudents.get(0).getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(activeStudents.get(1).getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value(activeStudents.get(1).getFirstName()));

        verify(studentService).getAllStudents(true);
    }
    @Test
    void givenStudentRequest_whenAddStudent_thenReturnSavedStudent() throws Exception{
        StudentRequest studentRequest = new StudentRequest();
        when(studentService.addStudent(any(Student.class))).thenReturn(student1);
        ObjectMapper objectMapper=new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentRequest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(student1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(student1.getFirstName()));

        verify(studentService).addStudent(any(Student.class));
    }
    @Test
    void givenStudentRequestWithNullValues_whenAddStudent_thenThrowException() throws Exception{
        when(studentService.addStudent(any(Student.class)))
                .thenThrow(new BadRequestException(ErrorCode.INVALID_FIRST_NAME, ErrorMessage.INVALID_FIRST_NAME));
        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": null,\"lastName\": \"Doe\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(containsString(ErrorMessage.INVALID_FIRST_NAME)));

    }
    @Test
    public void givenStudentId_whenDeleteStudent_thenReturn200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/students/{id}",student1.getId()))
                .andExpect(status().isOk());

        verify(studentService).deleteStudent(student1.getId());
    }
}
