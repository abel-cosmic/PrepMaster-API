package com.prepmaster.demo.teacher;

import com.prepmaster.demo.department.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TeacherRequestBody {
    private Teacher teacher;

    private Long departmentId;


}
