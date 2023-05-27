package com.prepmaster.demo.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TeacherStatistics {
    int numberOfQuestionsSolved;
    int numberOfQuestionsAttempted;
    int numberOfTestsTaken;
    double questionSuccessRate;
    int numberOfBundles;
    int numberOfQuestions;

    public TeacherStatistics(
            int numberOfQuestionsSolved,
            int numberOfQuestionsAttempted,
            int numberOfTestsTaken,
            int numberOfBundles,
            int numberOfQuestions
    ) {
        this.numberOfQuestionsSolved = numberOfQuestionsSolved;
        this.numberOfQuestionsAttempted = numberOfQuestionsAttempted;
        this.numberOfTestsTaken = numberOfTestsTaken;
        this.numberOfBundles = numberOfBundles;
        this.numberOfQuestions = numberOfQuestions;
        questionSuccessRate = (numberOfQuestionsSolved * 100.0) / numberOfQuestionsAttempted;
    }
}
