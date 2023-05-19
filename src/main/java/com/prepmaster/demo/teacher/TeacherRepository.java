package com.prepmaster.demo.teacher;

import com.prepmaster.demo.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    @Query("SELECT t FROM Teacher t WHERE t.id = :id")
    Optional<Teacher> findById(@Param("id") Long id);
    @Query("SELECT t FROM Teacher t WHERE t.department.id = :departmentId")
    Optional<Teacher> findAllByDepartment(@Param("departmentId")Long departmentId);
    @Query("SELECT t FROM Teacher t WHERE t.email = :email")
    Optional<Teacher> findAllByEmail(@Param("email")String email);
    @Transactional
    @Modifying
    @Query("DELETE FROM Teacher  t WHERE t.id = :id")
    void deleteById(@Param("id")Long id);
    @Transactional
    @Modifying
    @Query("DELETE FROM Teacher t WHERE t.email = :email")
    void deleteByEmail(@Param("email") String email);
    @Transactional
    @Modifying
    @Query("DELETE FROM Teacher t WHERE t.department.id = :departmentId")
    void deleteAllByDepartmentId(@Param("departmentId") Long departmentId);
    // PUT (Update) an existing student
    @Transactional
    @Modifying
    @Query("UPDATE Teacher SET firstName = :firstName, lastName = :lastName, email = :email, " +
            "phoneNumber = :phoneNumber, gender = :gender, password = :password WHERE id = :id")
    void updateStudent(@Param("id") Long id, @Param("firstName") String firstName, @Param("lastName") String lastName,
                       @Param("email") String email, @Param("phoneNumber") String phoneNumber,
                       @Param("gender") String gender, @Param("password") String password);
    //POST (Create) a student account
    /*TODO: If you want add POST(Create method).
     * I have tried to make a custom query, but
     * there is saveAndFlush() method that can POST,
     *  and it will be a waste of space only.
     * */
}
