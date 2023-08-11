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
    String query1 = "SELECT s FROM Student s where s.isActive=true";
    String query2 = "UPDATE Student s SET s.isActive=false where id=?1";

    String query3 = "SELECT s FROM Student s where s.isActive=true and id=?1";


    @Query(query1)
    public List<Student> findActive();

    @Query(query2)
    @Modifying
    @Transactional
    public void softDelete(UUID id);

    @Query(query3)
    public Optional<Student> findActiveID(UUID id);

}


