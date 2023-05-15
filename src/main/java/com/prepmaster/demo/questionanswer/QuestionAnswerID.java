package com.prepmaster.demo.questionanswer;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class QuestionAnswerID implements Serializable {
    @Column(name="test_id")
    private Long testId;
    @Column(name = "question_id")
    private Long questionId;

    public QuestionAnswerID() {
    }

    public QuestionAnswerID(Long testId, Long questionId) {
        this.testId = testId;
        this.questionId = questionId;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
    //we must generate the hashcode and equals function for the serializable class
    // because the methods and the class is incomplete it must pass
    // methods from the main class to be implemented inside it.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionAnswerID that = (QuestionAnswerID) o;
        return Objects.equals(testId, that.testId) && Objects.equals(questionId, that.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, questionId);
    }
}
