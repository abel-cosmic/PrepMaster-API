package com.prepmaster.demo.course;

import com.prepmaster.demo.department.Department;
import com.prepmaster.demo.department.DepartmentService;
import com.prepmaster.demo.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor //LOMBOK SEE VIDEO
@Slf4j // so we can use log variable
public class CourseService {
    private final CourseRepository courseRepository;
    private final DepartmentService departmentService;

    public Course getCourse(Long id){
        log.info("Getting course {}", id);
        Course course = courseRepository
                .findById(id)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Admin with ID " + id + " not found");
                            log.error("error course {} not found", id , notFoundException);
                            return notFoundException;
                        }
                );
        log.info("Got course {}", course.getId());
        return course;
    }
    void createNewCourse(CourseRequestBody courseRequestBody){
        Course course = courseRequestBody.getCourse();
        log.info("Creating course {}", course);
        Department department = departmentService.getDepartment(courseRequestBody.getDepartmentId());
        course.setDepartment(department);
        courseRepository.save(course);
        log.info("Created course {} successfully", course.getId());
    }

    void updateCourse(CourseRequestBody courseRequestBody){
        Course course = courseRequestBody.getCourse();
        log.info("Updating course {}", course.getId());
        Department department = departmentService.getDepartment(courseRequestBody.getDepartmentId());
        course.setDepartment(department);
        if (!courseRepository.existsById(course.getId())) {
            NotFoundException notFoundException = new NotFoundException("Course with ID " + course.getId() + " not found");
            log.error("error course {} not found could not update a non existing tuple", course.getId() , notFoundException);
            return;
        }
        courseRepository.save(course);
        log.info("Updated course {} successfully", course.getId());
    }

    void deleteCourse(Long id){
        log.info("Deleting course {}", id);
        if (!courseRepository.existsById(id)) {
            NotFoundException notFoundException = new NotFoundException("Course with ID " + id + " not found");
            log.error("error course {} not found could not delete a non existing tuple", id , notFoundException);
            return;
        }
        courseRepository.deleteById(id);
        log.info("Deleted course {} successfully", id);
    }

    List<Course> getCourses(){
        log.info("Getting all courses");
        List<Course> courses = courseRepository.findAll();
        log.info("Got all courses");
        return courses;
    }

}
