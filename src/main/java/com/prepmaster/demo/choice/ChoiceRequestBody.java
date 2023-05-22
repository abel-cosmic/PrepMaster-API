package com.prepmaster.demo.choice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ChoiceRequestBody {
    private Choice choice;
    private Long questionId;
}
