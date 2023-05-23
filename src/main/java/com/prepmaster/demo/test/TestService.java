package com.prepmaster.demo.test;

import com.prepmaster.demo.bundle.Bundle;
import com.prepmaster.demo.bundle.BundleService;
import com.prepmaster.demo.exception.NotFoundException;
import com.prepmaster.demo.student.Student;
import com.prepmaster.demo.student.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TestService {
    private final TestRepository testRepository;
    private final StudentService studentService;
    private final BundleService bundleService;

    public Test getTest(Long id){
        log.info("Getting test {}", id);
        Test test = testRepository
                .findById(id)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("test with ID " + id + " not found");
                            log.error("error test {} not found", id , notFoundException);
                            return notFoundException;
                        }
                );
        log.info("Got test {}", test.getId());
        return test;
    }
    void createNewTest(TestRequestBody testRequestBody){
        Test test = testRequestBody.getTest();
        log.info("Creating test {}", test);
        extracted(testRequestBody, test);
        log.info("Created test {} successfully", test.getId());
    }

    void updateTest(TestRequestBody testRequestBody){
        Test test = testRequestBody.getTest();
        log.info("Updating test {}", test);
        extracted(testRequestBody, test);
        log.info("Updated test {} successfully", test.getId());
    }

    private void extracted(TestRequestBody testRequestBody, Test test) {
        Bundle bundle = bundleService.getBundle(testRequestBody.getBundleId());
        Student student = studentService.getStudent(testRequestBody.getStudentId());
        test.setBundle(bundle);
        test.setStudent(student);
        testRepository.save(test);
    }

    void deleteTest(Long id){
        log.info("Deleting test {}", id);
        if (!testRepository.existsById(id)) {
            NotFoundException notFoundException = new NotFoundException("Test with ID " + id + " not found");
            log.error("error test {} not found could not delete a non existing tuple", id , notFoundException);
            return;
        }
        testRepository.deleteById(id);
        log.info("Deleted test {} successfully", id);
    }

    List<Test> getTests(){
        log.info("Getting all bundles");
        List<Test> tests = testRepository.findAll();
        log.info("Got all bundles");
        return tests;
    }

}
