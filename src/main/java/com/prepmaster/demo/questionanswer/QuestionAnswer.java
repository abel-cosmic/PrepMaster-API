package com.prepmaster.demo.questionanswer;

import com.prepmaster.demo.question.Question;
import com.prepmaster.demo.test.Test;
import jakarta.persistence.*;

@Entity(name = "QuestionAnswer")
@Table(name = "question_answer")
public class QuestionAnswer {
    @EmbeddedId
    private QuestionAnswerID id;
    @ManyToOne
    @MapsId("testId")

    @JoinColumn(name="test_id")
    private Test test;
    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name="question_id")
    private Question question;


    public QuestionAnswer() {
    }

    public QuestionAnswer(Test test, Question question) {
        this.test = test;
        this.question = question;
    }

    public QuestionAnswerID getId() {
        return id;
    }

    public void setId(QuestionAnswerID id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "id=" + id +
                ", test=" + test +
                ", question=" + question +
                '}';
    }
}
