package com.prepmaster.demo.bundle;

import com.prepmaster.demo.course.Course;
import com.prepmaster.demo.course.CourseService;
import com.prepmaster.demo.exception.NotFoundException;
import com.prepmaster.demo.teacher.Teacher;
import com.prepmaster.demo.teacher.TeacherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BundleService {
    private final BundleRepository bundleRepository;
    private final CourseService courseService;
    private final TeacherService teacherService;

    public Bundle getBundle(Long id){
        log.info("Getting bundle {}", id);
        Bundle bundle = bundleRepository
                .findById(id)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Bundle with ID " + id + " not found");
                            log.error("error bundle {} not found", id , notFoundException);
                            return notFoundException;
                        }
                );
        log.info("Got bundle {}", bundle.getId());
        return bundle;
    }

    void createNewBundle(BundleRequestBody bundleRequestBody){
        Bundle bundle = bundleRequestBody.getBundle();
        log.info("Creating bundle {}", bundle);
        extracted(bundleRequestBody, bundle);
        bundleRepository.save(bundle);
        log.info("Created bundle {} successfully", bundle.getId());
    }

    void updateBundle(BundleRequestBody bundleRequestBody){
        Bundle bundle = bundleRequestBody.getBundle();
        log.info("Updating bundle {}", bundle);
        extracted(bundleRequestBody, bundle);
        //TODO CHECK IF BUNDLE EXISTS
        bundleRepository.save(bundle);
        log.info("Updated bundle {} successfully", bundle.getId());
    }

    private void extracted(BundleRequestBody bundleRequestBody, Bundle bundle) {
        Course course = courseService.getCourse(bundleRequestBody.getCourseId());
        Teacher teacher = teacherService.getTeacher(bundleRequestBody.getTeacherId());
//        List<Question> questions = bundleRequestBody.getQuestions();
//        questions.forEach(
//                bundle::addQuestion
//        );
        bundle.setCourse(course);
        bundle.setTeacher(teacher);

    }

    void deleteBundle(Long id){
        log.info("Deleting bundle {}", id);
        if (!bundleRepository.existsById(id)) {
            NotFoundException notFoundException = new NotFoundException("Bundle with ID " + id + " not found");
            log.error("error bundle {} not found could not delete a non existing tuple", id , notFoundException);
            return;
        }
        bundleRepository.deleteById(id);
        log.info("Deleted bundle {} successfully", id);
    }

    List<Bundle> getBundles(){
        log.info("Getting all bundles");
        List<Bundle> bundles = bundleRepository.findAll();
        log.info("Got all bundles");
        return bundles;
    }
}
