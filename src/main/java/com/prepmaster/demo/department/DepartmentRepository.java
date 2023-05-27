package com.prepmaster.demo.department;

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

    @Query(
            "SELECT COUNT(*) " +
            "FROM Student s " +
            "WHERE s.department.id = ?1")
    Optional<Integer> getNumberOfStudents(Long id);

    @Query(
            "SELECT COUNT(*) " +
            "FROM Teacher t " +
            "WHERE t.department.id = ?1")
    Optional<Integer> getNumberOfTeachers(Long id);

    @Query(
            "SELECT COUNT(*) " +
            "FROM Course c " +
            "WHERE c.department.id = ?1")
    Optional<Integer> getNumberOfCourses(Long id);

    @Query(
            "SELECT count(*) " +
            "FROM Bundle b  " +
            "WHERE b.course.department.id = ?1"
    )
    Optional<Integer> getNumberOfBundles(Long id);

    @Query(
            "SELECT count(*) " +
            "FROM Question q  " +
            "WHERE q.bundle.course.department.id = ?1"
    )
    Optional<Integer> getNumberOfQuestions(Long id);

    @Query(
            "SELECT count(*) " +
            "FROM Test t  " +
            "WHERE t.bundle.course.department.id = ?1"
    )
    Optional<Integer> getNumberOfTests(Long id);
}
