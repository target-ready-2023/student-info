package com.target.studentinfo.respository;


import com.target.studentinfo.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    String getActiveStudentsQuery = "SELECT s FROM Student s where s.isActive=true";
    String getInactiveStudentsQuery = "UPDATE Student s SET s.isActive=false where id=?1";

    String getActiveStudentQuery = "SELECT s FROM Student s where s.isActive=true and id=?1";


    @Query(getActiveStudentsQuery)
    public List<Student> findAllActive();

    @Query(getInactiveStudentsQuery)
    @Modifying
    @Transactional
    public void softDeleteStudent(long id);

    @Query(getActiveStudentQuery)
    public Optional<Student> findByIdActive(long id);

}

