package com.prepmaster.demo.questionanswer;

import com.prepmaster.demo.question.Question;
import com.prepmaster.demo.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer,Long> {
    @Query("SELECT qa FROM QuestionAnswer qa WHERE qa.test = :test AND qa.question = :question")
    QuestionAnswer findByTestAndQuestion(@Param("test") Test test, @Param("question")Question question);
    @Query("SELECT qa.chosenIndex FROM QuestionAnswer qa WHERE qa.id.testId = :testId")
    Integer findChosenIndexByTestId(@Param("testId") Long testId);
    @Transactional
    @Modifying
    @Query("DELETE FROM QuestionAnswer qa WHERE qa.id = :questionAnswerId")
    void deleteById(@Param("questionAnswerId") QuestionAnswerID questionAnswerId);

//    @Query("")
}
