package com.prepmaster.demo.course;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/api/courses")
@AllArgsConstructor
@RestController
public class CourseController {
    private CourseService courseService;

    @PostMapping
    void createCourse(@Valid @RequestBody CourseRequestBody courseRequestBody){
        courseService.createNewCourse(courseRequestBody);
    }

    @GetMapping(path = "{courseId}")
    Course readCourse(@Valid @PathVariable("courseId") Long id) {
        return courseService.getCourse(id);
    }
    @PutMapping
    void updateAdmin(@Valid @RequestBody CourseRequestBody courseRequestBody){
        courseService.updateCourse(courseRequestBody);
    }
    @DeleteMapping(path = "{courseId}")
    void deleteAdmin(@PathVariable("courseId") Long id){
        courseService.deleteCourse(id);
    }
}
