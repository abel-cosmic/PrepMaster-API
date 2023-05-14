package com.prepmaster.demo.question;

import com.prepmaster.demo.bundle.Bundle;
import jakarta.persistence.*;

import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Question")
@Table(name = "question")
public class Question {
    @Id
    @SequenceGenerator(
            name = "question_sequence",
            sequenceName = "question_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "question_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "question",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String question;
    @Column(
            name = "answer",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private char answer;
    @Column(
            name = "explanation",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String explanation;
    @Column(
            name = "difficulty",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String difficulty;

    public Question() {
    }

    public Question(
            String question,
            List<Choices> choices,
            char answer,
            String explanation,
            String difficulty) {
        this.question = question;
//        this.choices = choices;
        this.answer = answer;
        this.explanation = explanation;
        this.difficulty = difficulty;
    }

    public Question(
            Long id,
            String question,
            List<Choices> choices,
            char answer,
            String explanation,
            String difficulty) {
        this.id = id;
        this.question = question;
//        this.choices = choices;
        this.answer = answer;
        this.explanation = explanation;
        this.difficulty = difficulty;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public char getAnswer() {
        return answer;
    }

    public void setAnswer(char answer) {
        this.answer = answer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
//                ", choices=" + choices +
                ", answer=" + answer +
                ", explanation='" + explanation + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }
}
