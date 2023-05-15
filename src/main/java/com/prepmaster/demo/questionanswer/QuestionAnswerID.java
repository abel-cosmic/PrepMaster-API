package com.prepmaster.demo.questionanswer;

import com.prepmaster.demo.bundle.Bundle;
import com.prepmaster.demo.question.Question;
import com.prepmaster.demo.student.Student;
import com.prepmaster.demo.test.Test;
import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class QuestionAnswerID implements Serializable {
    @ManyToOne(
            fetch = FetchType.LAZY //Why
    )
    @JoinColumn(
            name = "test_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "test_question_answer_id_id_fk"
            )
    )
    private Test test;

    @ManyToOne(
            fetch = FetchType.LAZY //Why
    )
    @JoinColumn(
            name = "question_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "question_question_answer_id_id_fk"
            )
    )
    private Question question;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
