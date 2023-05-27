package com.prepmaster.demo.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StudentStatistics {
    int numberOfQuestionsSolved;
    int numberOfQuestionsAttempted;
    int numberOfTestsTaken;
    double questionSuccessRate;

    public StudentStatistics(
            int numberOfQuestionsSolved,
            int numberOfQuestionsAttempted,
            int numberOfTestsTaken
    ) {
        this.numberOfQuestionsSolved = numberOfQuestionsSolved;
        this.numberOfQuestionsAttempted = numberOfQuestionsAttempted;
        this.numberOfTestsTaken = numberOfTestsTaken;
        questionSuccessRate = (numberOfQuestionsSolved * 100.0) / numberOfQuestionsAttempted;
    }
}
