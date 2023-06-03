package com.prepmaster.demo.department;

import com.prepmaster.demo.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DepartmentRequestBodyUpdate {
    private Department department;
    private Long adminId;
    private Long departmentHeadId;
}
