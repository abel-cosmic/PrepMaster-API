package com.prepmaster.demo.questionanswer;

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
            name = "answer_chosen",
            updatable = false,
            columnDefinition = "TEXT"
    )
    private char AnswerChosen;

    public QuestionAnswer(char answerChosen) {
        AnswerChosen = answerChosen;
    }

    public QuestionAnswer() {
    }
}
