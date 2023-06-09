package com.prepmaster.demo.department;

import com.prepmaster.demo.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DepartmentRequestBody {
    private Department department;
    private Long adminId;
    private Teacher departmentHead;
}
