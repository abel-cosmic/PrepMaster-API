package com.prepmaster.demo.teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
//    @Query("SELECT t FROM Teacher t WHERE t.id = :id")
//    Optional<Teacher> findById(@Param("id") Long id);
//    @Query("SELECT t FROM Teacher t WHERE t.department.id = :departmentId")
//    Optional<Teacher> findAllByDepartment(@Param("departmentId")Long departmentId);
//    @Query("SELECT t FROM Teacher t WHERE t.email = :email")
//    Optional<Teacher> findAllByEmail(@Param("email")String email);
//    @Transactional
//    @Modifying
//    @Query("DELETE FROM Teacher  t WHERE t.id = :id")
//    void deleteById(@Param("id")Long id);
}
