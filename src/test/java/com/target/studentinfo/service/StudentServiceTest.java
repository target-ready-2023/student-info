package com.target.studentinfo.service;

import com.target.studentinfo.exception.BadRequestException;

import com.target.studentinfo.model.Student;
import com.target.studentinfo.respository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.willDoNothing;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentService studentService;
    private Student student;
    @BeforeEach
    void setUp(){
        student=Student.builder()
                .id(1L)
                .firstName("Ram")
                .lastName("K")
                .emailId("ram@123.com")
                .bloodGroup("B+")
                .fatherName("Ashok")
                .lastName("Swathi")
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
    public void givenStudentId_whenGetStudentById_thenReturnExpectedStudent(){
        given(studentRepository.findByIdActive(1L))
                .willReturn(Optional.ofNullable(student));
        assertThat(studentService.getStudent(1L,true).get().getFirstName()).isEqualTo(student.getFirstName());
    }
    @Test
    void givenStudentsList_whenGetAllStudents_thenReturnStudentsList(){
        given(studentRepository.findAllActive())
                .willReturn(new ArrayList<>(Collections.singleton(student)));
        assertThat(studentService.getAllStudents(true).get(0).getFatherName())
                .isEqualTo(student.getFatherName());
    }
    @Test
    void givenStudentObject_whenAddStudent_thenReturnStudentObject(){

        given(studentRepository.save(student))
                .willReturn(student);
        Student addStudentResponse=studentService.addStudent(student);
        assertThat(addStudentResponse).isNotNull();
        assertThat(addStudentResponse.getId()).isEqualTo(student.getId());
        assertThat(addStudentResponse).isEqualTo(student);
    }

    @Test
    void givenStudentId_whenDeleteStudent_thenNothing(){
        willDoNothing().given(studentRepository).softDeleteStudent(1L);
        studentService.deleteStudent(1L);
    }
    @Test
    public void givenStudentWithNullValues_whenAddStudent_thenThrowsException() {
        Student test = new Student();
        test.setFirstName(null);
        //given(studentRepository.save(test))
        //      .willThrow(new BadRequestException(ErrorCode.INVALID_FIRST_NAME, "first name cannot be null"));
        Throwable exception = assertThrows(BadRequestException.class, () -> studentService.addStudent(test));
        assertEquals("first name cannot be null",exception.getMessage());
        System.out.println(exception.getMessage());

    }

}

