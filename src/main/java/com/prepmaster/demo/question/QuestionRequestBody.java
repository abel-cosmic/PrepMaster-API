package com.prepmaster.demo.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class QuestionRequestBody {
    private Question question;
    private Long bundleId;
}
