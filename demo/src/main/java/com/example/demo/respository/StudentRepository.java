package com.example.demo.respository;


import com.example.demo.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
//    List<Student> findByID(long id);
//    List<Student> findAll();
}


