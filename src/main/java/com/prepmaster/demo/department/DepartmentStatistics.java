package com.prepmaster.demo.department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class DepartmentStatistics {
    int numberOfStudents;
    int numberOfTeachers;
    int numberOfCourses;
    int numberOfBundles;
    int numberOfQuestions;
    int numberOfTests;
}
