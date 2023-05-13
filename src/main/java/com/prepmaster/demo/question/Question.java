package com.prepmaster.demo.question;

import java.util.List;

public class Question {
    private Long id;
    private String question;
    private List<String> choices;
    private char answer;
    private String explanation;
    private String difficulty;

    public Question() {
    }

    public Question(
            String question,
            List<String> choices,
            char answer,
            String explanation,
            String difficulty) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
        this.explanation = explanation;
        this.difficulty = difficulty;
    }

    public Question(
            Long id,
            String question,
            List<String> choices,
            char answer,
            String explanation,
            String difficulty) {
        this.id = id;
        this.question = question;
        this.choices = choices;
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

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
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
                ", choices=" + choices +
                ", answer=" + answer +
                ", explanation='" + explanation + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }
}
