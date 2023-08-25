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
    String getStudentsQuery = "SELECT s FROM Student s where s.isActive=?1 and s.standard in ?2";
    String deleteStudentQuery = "UPDATE Student s SET s.isActive=false where id=?1";

    String getStudentQuery = "SELECT s FROM Student s where s.isActive=?1 and id=?2";




    @Query(getStudentsQuery)
    public List<Student> getStudents(Boolean isActive, List<Integer> standardList);


    @Query(deleteStudentQuery)
    @Modifying
    @Transactional
    public void deleteStudent(long id);

    @Query(getStudentQuery)
    public Optional<Student> getStudent(Boolean isActive, long id);

}

