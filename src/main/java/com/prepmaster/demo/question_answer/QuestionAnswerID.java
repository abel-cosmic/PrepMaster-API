package com.prepmaster.demo.question_answer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class QuestionAnswerID implements Serializable {
    @Column(name = "test_id")
    private Long testId;
    @Column(name = "question_id")
    private Long questionId;
    @Column(name = "student_id")
    private Long studentId;

    public QuestionAnswerID(Long testId, Long questionId, Long studentId) {
        this.testId = testId;
        this.questionId = questionId;
        this.studentId = studentId;
    }

    public QuestionAnswerID() {

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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
