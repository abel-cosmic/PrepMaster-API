package com.prepmaster.demo.question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Query("SELECT q FROM Question q WHERE q.id = :id")
    Optional<Question> findById(@Param("id") Long id);
    @Query("SELECT q FROM Question q LEFT JOIN FETCH q.choices WHERE q.id = :questionId")
    Question findQuestionWithChoices(@Param("questionId") Long questionId);
    @Query("SELECT q, q.answerIndex FROM Question q LEFT JOIN FETCH q.choices WHERE q.id = :questionId")
    Object[] findQuestionWithChoicesAndAnswerIndex(@Param("questionId") Long questionId);

    @Query(value = "select choice_text from choice c JOIN question q on q.id = ?1 and q.id = c.question_id and q.answer = c.index", nativeQuery = true) //TODO: MAKE NON NATIVE
    Optional<String> findChoiceTextByQuestionIdAndAnswer(Long questionId);
}
