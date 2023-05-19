package com.prepmaster.demo.question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Query("SELECT q FROM Question q WHERE q.id = :id")
    Optional<Question> findById(@Param("id") Long id);
    @Query("SELECT q FROM Question q LEFT JOIN FETCH q.choices WHERE q.id = :questionId")
    Question findQuestionWithChoices(@Param("questionId") Long questionId);
    @Query("SELECT q, q.answerIndex FROM Question q LEFT JOIN FETCH q.choices WHERE q.id = :questionId")
    Object[] findQuestionWithChoicesAndAnswerIndex(@Param("questionId") Long questionId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Question q WHERE q.id = :questionId")
    void deleteById(@Param("questionId") Long questionId);

}
