package com.prepmaster.demo.questionanswer;

import com.prepmaster.demo.bundle.Bundle;
import com.prepmaster.demo.question.Question;
import com.prepmaster.demo.student.Student;
import com.prepmaster.demo.test.Test;
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
