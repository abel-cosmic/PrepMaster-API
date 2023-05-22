package com.prepmaster.demo.bundle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BundleRequestBody {
    private Bundle bundle;
    private Long courseId, teacherId;
}
