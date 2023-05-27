package com.prepmaster.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
//    @Query("SELECT s FROM Student s WHERE s.id = :id")
//    Optional<Student> findById(@Param("id") Long id);
//    @Query("SELECT s FROM Student s WHERE s.department.id = :departmentId")
//    Optional<Student> findAllByDepartment(@Param("departmentId")Long departmentId);
//    @Query("SELECT s FROM Student s WHERE s.email = :email")
//    Optional<Student> findAllByEmail(@Param("email")String email);
    @Query(
        "SELECT count(*) AS c " +
        "FROM QuestionAnswer qa " +
        "JOIN Student s ON qa.test.student.id = s.id "+
        "JOIN Question q ON qa.question.id = q.id " +
        "WHERE q.answerIndex = qa.chosenIndex AND s.id = ?1"
    )
    Optional<Integer> getNumberOfQuestionsSolved(Long id);

    @Query(
        "SELECT COUNT(*) AS c " +
        "FROM QuestionAnswer qa " +
        "WHERE qa.test.student.id = ?1"
    )
    Optional<Integer> getNumberOfQuestionsAttempted(Long id);

    @Query(
        "SELECT COUNT(*) AS c " +
        "FROM Test t " +
        "WHERE t.student.id = ?1 "
    )
    Optional<Integer> getNumberOfTestsTaken(Long id);
}
