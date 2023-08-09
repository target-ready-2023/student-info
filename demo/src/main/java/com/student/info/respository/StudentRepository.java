package com.student.info.respository;


import com.student.info.model.Student;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


//@SQLDelete(sql="UPDATE Student SET is_deleted = true where id = ?")
//@Where(clause = "is_deleted=false")
@Repository
public interface StudentRepository extends JpaRepository<Student, UUID>{
    //add override code for soft delete
    //studentrepoimpl
    @Override
    @Query("SELECT s FROM Student s where s.isDeleted=false")
    public List<Student> findAll();

    @Query("SELECT s FROM Student s where s.isDeleted=true")
    public List<Student> findDeleted();

    @Query("UPDATE Student s SET s.isDeleted=true where id=?1")
    @Modifying
    @Transactional
    public void softDelete(UUID id);
    //archived
}


