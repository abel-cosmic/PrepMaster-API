package com.prepmaster.demo.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CourseRequestBody   {
    private Course course;
    private Long departmentId;

}
