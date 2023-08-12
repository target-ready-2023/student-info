package com.target.studentinfo.respository;


import com.target.studentinfo.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID>{
    String findActiveStudents = "SELECT s FROM Student s where s.isActive=true";
    String softDelete = "UPDATE Student s SET s.isActive=false where id=?1";
    String findActiveStudentsID = "SELECT s FROM Student s where s.isActive=true and id=?1";


    @Query(findActiveStudents)
    public List<Student> findActive();

    @Query(softDelete)
    @Modifying
    @Transactional
    public void softDelete(UUID id);

    @Query(findActiveStudentsID)
    public Optional<Student> findActiveID(UUID id);

}