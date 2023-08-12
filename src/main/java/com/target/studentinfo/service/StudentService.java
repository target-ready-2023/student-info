package com.target.studentinfo.service;
import com.target.studentinfo.exception.ErrorCode;
import com.target.studentinfo.exception.ErrorMessage;
import com.target.studentinfo.exception.NotFoundException;
import com.target.studentinfo.model.Student;
import com.target.studentinfo.model.StudentValidator;
import com.target.studentinfo.respository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents(Boolean isActive){
        return isActive ? studentRepository.findAllActive() : studentRepository.findAll();
    }

    public Optional<Student> getStudent(long id , Boolean isActive){
       return isActive ? studentRepository.findByIdActive(id) : studentRepository.findById(id);
    }

    public Student addStudent(Student student){
        StudentValidator.validateStudent(student);
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student , long id) {
        student.setId(id);
        if (!studentRepository.existsById(id)) {
            throw new NotFoundException(ErrorCode.STUDENT_NOT_FOUND, ErrorMessage.STUDENT_NOT_FOUND);
        }

        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {studentRepository.softDeleteStudent(id);}
}





