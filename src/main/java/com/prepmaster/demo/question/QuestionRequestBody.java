package com.prepmaster.demo.question;

import com.prepmaster.demo.choice.Choice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class QuestionRequestBody {
    private Question question;
    private Long bundleId;
    private List<Choice> choices;
}
