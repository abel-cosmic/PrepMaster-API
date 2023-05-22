package com.prepmaster.demo.department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DepartmentRequestBody {
    private Department department;
    private Long adminId;
    private Long departmentHeadId;
}
