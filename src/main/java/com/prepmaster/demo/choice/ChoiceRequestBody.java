package com.prepmaster.demo.choice;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChoiceRequestBody {
    private Choice choice;
    private Long questionId;

    public Choice getChoice() {
        return choice;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
