package com.prepmaster.demo.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TestRequestBody {
    private Test test;
    private Long bundleId, studentId;
}
