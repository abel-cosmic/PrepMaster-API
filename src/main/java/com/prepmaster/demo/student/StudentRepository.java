package com.prepmaster.demo.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("SELECT s FROM Student s WHERE s.id = :id")
    Optional<Student> findById(@Param("id") Long id);
    @Query("SELECT s FROM Student s WHERE s.department.id = :departmentId")
    Optional<Student> findAllByDepartment(@Param("departmentId")Long departmentId);
    @Query("SELECT s FROM Student s WHERE s.email = :email")
    Optional<Student> findAllByEmail(@Param("email")String email);
    @Transactional
    @Modifying
    @Query("DELETE FROM Student  s WHERE s.id = :id")
    void deleteById(@Param("id") Long id);
}
