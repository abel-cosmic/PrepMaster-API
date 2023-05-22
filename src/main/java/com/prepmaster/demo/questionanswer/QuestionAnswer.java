package com.prepmaster.demo.questionanswer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.prepmaster.demo.question.Question;
import com.prepmaster.demo.test.Test;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity(name = "QuestionAnswer")
@Table(name = "question_answer")
public class QuestionAnswer {
    //this connects the serializable class to the main class
    //QuestionAnswerId
    @EmbeddedId
    private QuestionAnswerID id;
    //we make the relationship of the questionAnswer to test

    @NotNull(message = "Chosen index allowed must not be NULL")
    @Column(
            name = "chosen_index",
            nullable = false,
            columnDefinition = "INT"
    )
    private int chosenIndex;

    @ManyToOne
    @MapsId("testId")
    @JoinColumn(
            name="test_id",
            foreignKey = @ForeignKey(
                    name = "test_id_fk"
            )
    )
    private Test test;
    //we make the relationship of the questionAnswer to question
    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(
            name="question_id",
            foreignKey = @ForeignKey(
                    name = "question_id_fk"
            )
    )
    private Question question;

    public QuestionAnswer(QuestionAnswerID id, int chosenIndex, Test test, Question question) {
        this.id = id;
        this.chosenIndex = chosenIndex;
        this.test = test;
        this.question = question;
    }

    public QuestionAnswer() {
    }

    public QuestionAnswer(Test test, Question question, int chosenIndex) {
        this.test = test;
        this.question = question;
        this.chosenIndex= chosenIndex;
    }

    @JsonIgnore
    public QuestionAnswerID getId() {
        return id;
    }

    public void setId(QuestionAnswerID id) {
        this.id = id;
    }
    @JsonIgnore
    public Test getTest() {
        return test;
    }

    @JsonProperty("testId")
    public Long getTestId() {
        return test.getId();
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @JsonIgnore
    public Question getQuestion() {
        return question;
    }

    @JsonProperty("questionId")
    public Long getQuestionId() {
        return question.getId();
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getChosenIndex() {
        return chosenIndex;
    }

    public void setChosenIndex(int chosenIndex) {
        this.chosenIndex = chosenIndex;
    }

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "id=" + id +
                ", test=" + test +
                ", question=" + question +
                ", chosenIndex=" + chosenIndex +
                '}';
    }
}
