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
            "JOIN Test t ON t.id = qa.test.id " +
            "JOIN Bundle b ON t.bundle.id = b.id " +
            "JOIN Teacher tr on b.teacher.id = tr.id " +
            "JOIN Question q on qa.question.id = q.id " +
            "WHERE q.answerIndex = qa.chosenIndex and tr.id = ?1"
    )
    Optional<Integer> getNumberOfQuestionsSolved(Long id);

    @Query(
            "SELECT COUNT(*) AS c " +
            "FROM QuestionAnswer qa " +
            "JOIN Test t on t.id = qa.test.id " +
            "JOIN Bundle b on b.id = t.bundle.id " +
            "WHERE b.teacher.id = ?1"
    )
    Optional<Integer> getNumberOfQuestionsAttempted(Long id);
    @Query(

            "SELECT count(*) AS c " +
            "FROM Bundle b " +
            "WHERE b.teacher.id = ?1")
    Optional<Integer> getNumberOfBundles(Long id);

    @Query(
            "SELECT COUNT(*) AS c " +
            "FROM Bundle b " +
            "JOIN Question q ON b.id = q.bundle.id " +
            "WHERE b.teacher.id = ?1"
    )
    Optional<Integer> getNumberOfQuestions(Long id);

    @Query(
            "SELECT COUNT(*) AS c " +
            "FROM Bundle b " +
            "JOIN Test t ON b.id = t.bundle.id " +
            "WHERE b.teacher.id = ?1"
    )
    Optional<Integer> getNumberOfTestsTaken(Long id);
}
