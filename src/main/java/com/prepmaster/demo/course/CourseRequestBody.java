package com.prepmaster.demo.course;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseRequestBody {
    private Course course;
    private Long departmentId;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
