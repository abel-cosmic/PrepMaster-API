package com.prepmaster.demo.questionanswer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class QuestionAnswerRequestBody {
    private QuestionAnswer questionAnswer;
    private Long testId, questionId;
}
