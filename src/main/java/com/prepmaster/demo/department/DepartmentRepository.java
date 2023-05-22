package com.prepmaster.demo.department;

import com.prepmaster.demo.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    @Query("SELECT d FROM Department d WHERE d.id = :id")
    Optional<Department> findById(@Param("id") Long id);
    @Query("SELECT d FROM Department d WHERE d.admin.id = :adminId")
    Optional<Department> findAllByAdminId(@Param("adminId")Long adminId);
    @Query("SELECT d FROM Department d WHERE d.departmentHead.id = :departmentHeadId")
    Optional<Department> findAllByDepartmentHeadId(@Param("departmentHeadId")Long departmentHeadId);

}
