package com.target.studentinfo.service;

import com.target.studentinfo.exception.BadRequestException;
import com.target.studentinfo.exception.ErrorMessage;
import com.target.studentinfo.model.Student;
import com.target.studentinfo.respository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentService studentService;
    private Student student1,student2,student3,studentWithNullValues;
    private List<Student> mockStudents=new ArrayList<>();
    @BeforeEach
    void setUp(){
        student1=new Student(1L, "Ram", "k", "ram@123.com", "A+", "Ashok", "Swathi", 12, "male", "hyderabad", 8, "painting", "none", "self", true);
        student2=new Student(2L, "John", "Deo", "john@123.com", "B+", "Smith", "Jane", 14, "male", "kerala", 10, "music", "dust", "self", true);
        student3=new Student(3L, "Raksh", "k", "raksh@123.com", "A+", "Ashok", "Swathi", 10, "male", "hyderabad", 6, "painting", "none", "self", true);
        mockStudents.add(student1);
        mockStudents.add(student2);
        mockStudents.add(student3);
    }
    @Test
    public void givenStudentId_whenGetStudentById_thenReturnExpectedStudent(){
        given(studentRepository.findByIdActive(student1.getId()))
                .willReturn(Optional.ofNullable(student1));
        Student expectedStudent=studentService.getStudent(student1.getId(),true).get();
        assertThat(expectedStudent).isNotNull();
        assertThat(expectedStudent.getId()).isEqualTo(student1.getId());
        assertThat(expectedStudent).isEqualTo(student1);
    }
    @Test
    void givenStudentsList_whenGetAllStudents_thenReturnStudentsList(){
        given(studentRepository.findAllActive())
                .willReturn(mockStudents);
        List<Student> expectedStudents=studentService.getAllStudents(true);
        assertEquals(mockStudents.size(),expectedStudents.size());
        assertEquals(expectedStudents,mockStudents);
    }
    @Test
    void givenStudentRequest_whenAddStudent_thenReturnStudentResponse(){

        given(studentRepository.save(student1))
                .willReturn(student1);
        Student addedStudent=studentService.addStudent(student1);
        assertThat(addedStudent).isNotNull();
        assertThat(addedStudent.getId()).isEqualTo(student1.getId());
        assertThat(addedStudent).isEqualTo(student1);
    }

    @Test
    void givenStudentId_whenDeleteStudent_thenNothing(){
        willDoNothing().given(studentRepository).softDeleteStudent(student1.getId());
        studentService.deleteStudent(student1.getId());
        verify(studentRepository,times(1)).softDeleteStudent(student1.getId());
    }
    @Test
    public void givenStudentWithNullValues_whenAddStudent_thenThrowsException() {
        studentWithNullValues = new Student();
        studentWithNullValues.setFirstName(null);
        Exception exception = assertThrows(BadRequestException.class, () -> studentService.addStudent(studentWithNullValues));
        assertEquals(ErrorMessage.INVALID_FIRST_NAME,exception.getMessage());
    }

}

