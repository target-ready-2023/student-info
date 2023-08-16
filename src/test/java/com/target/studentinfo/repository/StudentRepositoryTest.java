package com.target.studentinfo.repository;


import com.target.studentinfo.model.Student;
import com.target.studentinfo.respository.StudentRepository;
import com.target.studentinfo.service.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;




@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @MockBean
    private StudentService studentService;
    Student student;

    @BeforeEach
    void setUp() {
        Student activeStudent1 = new Student(1L, "Ram", "k", "ram@123.com", "A+", "Ashok", "Swathi", 12, "male", "hyderabad", 8, "painting", "none", "self", true);
        Student activeStudent2 = new Student(2L, "John", "Deo", "john@123.com", "B+", "Smith", "Jane", 14, "male", "kerala", 10, "music", "dust", "self", true);
        Student inactiveStudent1 = new Student(3L, "Raksh", "k", "raksh@123.com", "A+", "Ashok", "Swathi", 10, "male", "hyderabad", 6, "painting", "none", "self", false);

        studentRepository.save(activeStudent1);
        studentRepository.save(activeStudent2);
        studentRepository.save(inactiveStudent1);

        studentRepository.flush();

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    public void testFindAllActive() {
        List<Student> activeStudents = studentRepository.findAllActive();

        System.out.println("Active Students:");
        for (Student student : activeStudents) {
            System.out.println(student);
        }

        assertEquals(2, activeStudents.size());
        for (Student student : activeStudents) {
            assertTrue(student.isActive());
        }
    }

    @Test
    public void testSoftDeleteStudent() {
        Student activeStudent = new Student(1L, "Rashmi", "H", "rashmi@123.com", "AB+", "Ramesh", "Pooja", 15, "female", "Karnataka", 10, "Dancing", "none", "self", false);
        studentRepository.save(activeStudent);


        Student savedStudent = studentRepository.save(activeStudent);


        System.out.println("Before Soft Delete: " + activeStudent);


        studentRepository.softDeleteStudent(activeStudent.getId());


        System.out.println("After Soft Delete: " + savedStudent);


        Optional<Student> deletedStudentOptional = studentRepository.findById(activeStudent.getId());
        assertTrue(deletedStudentOptional.isPresent());
        Student deletedStudent = deletedStudentOptional.get();
        assertFalse(deletedStudent.isActive());


        studentRepository.delete(deletedStudent);
    }


    @Test
    public void testFindActiveStudentById() {

        Student activeStudent = new Student(4L, "Rashmi", "H", "rashmi@123.com", "AB+", "Ramesh", "Pooja", 15, "female", "Karnataka", 10, "Dancing", "none", "self", true);
        studentRepository.save(activeStudent);


        Optional<Student> retrievedStudentOptional = studentRepository.findByIdActive(activeStudent.getId());


        assertTrue("Student should be present", retrievedStudentOptional.isPresent());
        Student retrievedStudent = retrievedStudentOptional.get();
        assertTrue("Student should be active", retrievedStudent.isActive());
    }



}
