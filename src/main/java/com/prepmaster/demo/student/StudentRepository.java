package com.prepmaster.demo.student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    void deleteById(@Param("id")Long id);
    @Transactional
    @Modifying
    @Query("DELETE FROM Student s WHERE s.email = :email")
    void deleteByEmail(@Param("email") String email);
    @Transactional
    @Modifying
    @Query("DELETE FROM Student s WHERE s.department.id = :departmentId")
    void deleteAllByDepartmentId(@Param("departmentId") Long departmentId);
    // PUT (Update) an existing student
}
