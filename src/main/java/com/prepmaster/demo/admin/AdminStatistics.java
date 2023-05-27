package com.prepmaster.demo.admin;

import com.prepmaster.demo.department.Department;
import com.prepmaster.demo.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class AdminStatistics {
    int numberOfStudents;
    int numberOfTeachers;
    int numberOfCourses;
    int numberOfDepartments;
//    List<Department> mostAchievingDepartments;
//    List<Student> mostAchievingStudent;

}
