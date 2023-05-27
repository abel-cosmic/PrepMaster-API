package com.prepmaster.demo.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
    @Query(
            "SELECT count(*) AS c " +
            "FROM QuestionAnswer qa " +
            "JOIN Teacher t ON qa.test.bundle.teacher.id = t.id " +
            "JOIN Question q on qa.question.id = q.id " +
            "WHERE q.answerIndex = qa.chosenIndex and t.id = ?1"
    )
    Optional<Integer> getNumberOfQuestionsSolved(Long id);

    @Query(
            "SELECT COUNT(*) AS c " +
            "FROM QuestionAnswer qa " +
            "WHERE qa.test.bundle.teacher.id = ?1"
    )
    Optional<Integer> getNumberOfQuestionsAttempted(Long id);
    @Query(

            "SELECT count(*) AS c " +
            "FROM Bundle b " +
            "WHERE b.teacher.id = ?1")
    Optional<Integer> getNumberOfBundles(Long id);

    @Query(
            "SELECT COUNT(*) AS c " +
            "FROM Question q " +
            "WHERE q.bundle.teacher.id = ?1"
    )
    Optional<Integer> getNumberOfQuestions(Long id);

    @Query(
            "SELECT COUNT(*) AS c " +
            "FROM Test t " +
            "WHERE t.bundle.teacher.id = ?1"
    )
    Optional<Integer> getNumberOfTestsTaken(Long id);
}
