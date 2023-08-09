package com.target.studentinfo.respository;


import com.target.studentinfo.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID>{
    @Override
    @Query("SELECT s FROM Student s where s.isDeleted=false")
    public List<Student> findAll();

    @Query("SELECT s FROM Student s where s.isDeleted=true")
    public List<Student> findArchived();

    @Query("UPDATE Student s SET s.isDeleted=true where id=?1")
    @Modifying
    @Transactional
    public void archive(UUID id);
}


