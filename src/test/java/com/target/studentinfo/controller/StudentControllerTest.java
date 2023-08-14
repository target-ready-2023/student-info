package com.target.studentinfo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.studentinfo.dto.request.StudentRequest;
import com.target.studentinfo.exception.BadRequestException;
import com.target.studentinfo.exception.ErrorCode;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
@ComponentScan(basePackages = "com.target.studentinfo")
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;
    Student student;
    private List<Student> mockStudents;
    @BeforeEach
    void setup(){
        mockStudents = Arrays.asList(
                new Student(1L, "Ram", "k", "ram@123.com", "A+", "Ashok", "Swathi", 12, "male", "hyderabad", 8, "painting", "none", "self", false),
                new Student(2L, "John", "Deo", "john@123.com", "B+", "Smith", "Jane", 14, "male", "kerala", 10, "music", "dust", "self", true),
                new Student(3L, "Raksh", "k", "raksh@123.com", "A+", "Ashok", "Swathi", 10, "male", "hyderabad", 6, "painting", "none", "self", false)
        );
        student=Student.builder()
                .id(1L)
                .firstName("Ram")
                .lastName("K")
                .emailId("ram@123.com")
                .bloodGroup("A+")
                .fatherName("Ashok")
                .motherName("Swathi")
                .age(12)
                .gender("male")
                .address("Hyderabad")
                .standard(8)
                .extraCurricular("painting")
                .allergies("none")
                .transport("self")
                .isActive(true).build();


    }
    @Test
    public void givenStudentId_whenGetStudentById_thenReturnStudentObject() throws Exception {
        long studentId = 1L;
        when(studentService.getStudent(studentId, true)).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders.get("/students/{id}", studentId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Ram"));
        verify(studentService).getStudent(1L, true);
    }
    @Test
    public void givenListOfEmployees_whenGetAllEmployees_thenReturnEmployeesList() throws Exception {
        when(studentService.getAllStudents(true)).thenReturn(mockStudents);

        mockMvc.perform(MockMvcRequestBuilders.get("/students"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Ram"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value("John"));

        verify(studentService).getAllStudents(true);
    }
    @Test
    void givenStudentObject_whenCreateStudent_thenReturnSavedStudent() throws Exception{
        Student newStudent = new Student(3L, "Raj", "k", "raj@123.com", "AB+", "Amit", "Sakshi", 11, "Female", "Karnataka", 7, "painting", "dust", "bus", true);
        StudentRequest studentRequest = new StudentRequest();

        when(studentService.addStudent(any(Student.class))).thenReturn(newStudent);
        ObjectMapper objectMapper=new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentRequest)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Raj"));

        verify(studentService).addStudent(any(Student.class));
    }
    @Test
    void givenStudentObjectWithNullValues_whenAddStudent_thenThrowException() throws Exception{
        when(studentService.addStudent(any(Student.class)))
                .thenThrow(new BadRequestException(ErrorCode.INVALID_FIRST_NAME, "first name cannot be null"));
        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": null,\"lastName\": \"Doe\"}"))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string(containsString("first name cannot be null")));

    }
    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturn200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/students/1"))
                .andExpect(status().isOk());

        verify(studentService).deleteStudent(1L);
    }
}
