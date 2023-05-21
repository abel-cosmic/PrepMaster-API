package com.prepmaster.demo.student;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StudentRequestBody {
    private Student student;
    private Long departmentId;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
