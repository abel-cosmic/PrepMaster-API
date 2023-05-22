package com.prepmaster.demo.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StudentRequestBody {
    private Student student;
    private Long departmentId;
}
