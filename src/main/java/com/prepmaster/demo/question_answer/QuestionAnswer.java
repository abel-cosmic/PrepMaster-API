package com.prepmaster.demo.question_answer;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "QuestionAnswer")
@Table(
        name = "question_answer"

)
public class QuestionAnswer {
    @EmbeddedId
    private QuestionAnswerID id;

    @Column(
            name = "answer_chossen",
            updatable = false,
            columnDefinition = "TEXT"
    )
    private char AnswerChoosen;

    public QuestionAnswer(char answerChoosen) {
        AnswerChoosen = answerChoosen;
    }

    public QuestionAnswer() {
    }
}
