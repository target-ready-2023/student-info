package com.target.studentinfo.ControllerTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.studentinfo.controller.StudentController;
import com.target.studentinfo.model.Student;
import com.target.studentinfo.respository.StudentRepository;
import com.target.studentinfo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentRepository studentRepository;
    @MockBean
    private StudentService studentService;




    @Test
    public void testAddNewStudent() throws Exception {
// Create a mock Student object and specify its expected behavior
        Student mockStudent = new Student();

        mockStudent.setFirstName("John");
        mockStudent.setLastName("Doe");
        mockStudent.setEmailId("John@gmail.com");
        mockStudent.setBloodGroup("B+");
        mockStudent.setFatherName("John");
        mockStudent.setMotherName("John");
        mockStudent.setAge(12);
        mockStudent.setGender("John");
        mockStudent.setAddress("John");
        mockStudent.setStandard(1);
        mockStudent.setExtraCurricular("playing");
        mockStudent.setAllergies("None");
        mockStudent.setTransport("self");


        when(studentService.addNewStudent(any(Student.class))).thenReturn(mockStudent);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(mockStudent);
// Perform the POST request to the /student endpoint with a JSON body
        mockMvc.perform(MockMvcRequestBuilders.post("/student_information_service/v1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
// .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.content().json(requestBody));
// .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"));

// Verify that the studentService.addNewStudent method was called with the correct argument
        verify(studentService, times(1)).addNewStudent(any(Student.class));
    }



    @Test
    public void testAddNewStudentWithMissingFields() throws Exception {
        // Perform the POST request with missing fields
        mockMvc.perform(MockMvcRequestBuilders.post("/student_information_service/v1/student/resp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"lastName\": \"Doe\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(containsString("Student request body contains null parameters")));

        // Verify that the studentService.addNewStudent method was not called
        verify(studentService, never()).addNewStudent(any(Student.class));
    }

    @Test
    public void testAddNewStudentWithInvalidData() throws Exception {
        // Perform the POST request with invalid data (e.g., negative age)
        MvcResult result=mockMvc.perform(MockMvcRequestBuilders.post("/student_information_service/v1/student/resp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"Age\": one }"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
//                .andExpect(MockMvcResultMatchers.content().string(containsString("")));

        // Verify that the studentService.addNewStudent method was not called
        String responseContent = result.getResponse().getContentAsString();
        System.out.println("Response Content: " + responseContent);
        verify(studentService, never()).addNewStudent(any(Student.class));
    }

    @Test
    public void testAddNewStudentWithNullRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/student_information_service/v1/student/resp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().is(400))
                .andExpect(MockMvcResultMatchers.content().string(containsString("Student request body contains null parameters.")));

    }



    @Test
    public void testDeleteStudent() throws Exception {
        long studentId = 1L;
// Perform the DELETE request
        mockMvc.perform(MockMvcRequestBuilders.delete("/student_information_service/v1/student/{id}", studentId))
                .andExpect(status().isOk());

// Verify that the studentService.deleteStudent method was called with the correct argument
        verify(studentService, times(1)).deleteStudent(studentId);
    }

//    @Test
//    public void testUpdateStudent_Success() throws Exception {
//
//
//        Student studentToUpdate = new Student();
//        studentToUpdate.setFirstName("Joe");
//        studentToUpdate.setLastName("Doe");
//        studentToUpdate.setEmailId("john@example.com");
//
//        when(studentRepository.save(any(Student.class))).thenReturn(studentToUpdate);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String requestBody = objectMapper.writeValueAsString(studentToUpdate);
//
//        // Act and Assert
//        mockMvc.perform(MockMvcRequestBuilders.post("/student_information_service/v1/student")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestBody))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Joe"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"))
//                .andExpect(MockMvcResultMatchers.content().json(requestBody));
//
//        verify(studentRepository, times(1)).save(any(Student.class));
//    }
}