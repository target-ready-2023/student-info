package com.target.studentinfo.repository;

import com.target.studentinfo.model.Student;
import com.target.studentinfo.respository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StudentRepositoryTest {

    @Mock
    private StudentRepository studentRepository;
    private Student activeStudent1, activeStudent2;
    private Student inActiveStudent;

    @BeforeEach
    void setUp() {
        activeStudent1 = new Student(1L, "Rashmi", "H", "rashmi@123.com", "AB+", "Ramesh", "Pooja", 15, "female", "Karnataka", 10, "Dancing", "none", "self", true);
        activeStudent2 = new Student(2L, "John", "Doe", "john@abc.com", "O-", "Michael", "Jane", 20, "male", "New York", 12, "Singing", "none", "public", true);
        inActiveStudent = new Student(3L, "Raj", "K", "raj@abc.com", "A+", "Ramesh", "Pooja", 10, "male", "India", 5, "Dancing", "Dust", "self", false);
    }

    @Test
    public void givenActiveStudents_whenFindAllActive_thenReturnActiveStudentsList() {
        given(studentRepository.findAllActive())
                .willReturn(List.of(activeStudent1, activeStudent2));
        List<Student> result = studentRepository.findAllActive();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(activeStudent1.getId());
        assertThat(result.get(1).getId()).isEqualTo(activeStudent2.getId());
    }

    @Test
    public void givenStudentId_whenSoftDeleteStudent_thenStudentShouldBeInactive() {
        studentRepository.softDeleteStudent(activeStudent2.getId());
        verify(studentRepository, times(1)).softDeleteStudent(activeStudent2.getId());
    }

    @Test
    public void givenStudentId_whenFindByIdActive_thenReturnExpectedStudent() {
        given(studentRepository.findByIdActive(activeStudent1.getId()))
                .willReturn(Optional.ofNullable(activeStudent1));
        Optional<Student> result = studentRepository.findByIdActive(activeStudent1.getId());
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getFirstName()).isEqualTo(activeStudent1.getFirstName());
    }

    @Test
    public void givenInactiveStudent_whenFindByIdActive_thenReturnEmptyOptional() {
        given(studentRepository.findByIdActive(inActiveStudent.getId()))
                .willReturn(Optional.empty());
        Optional<Student> result = studentRepository.findByIdActive(inActiveStudent.getId());
        assertThat(result.isPresent()).isFalse();
    }

}


