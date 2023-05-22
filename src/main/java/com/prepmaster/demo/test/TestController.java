package com.prepmaster.demo.test;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/api/tests")
@AllArgsConstructor
@RestController
public class TestController {
    private final TestService testService;

    @PostMapping
    void createTest(@Valid @RequestBody TestRequestBody testRequestBody){
        testService.createNewTest(testRequestBody);
    }
    @GetMapping(path = "{testId}")
    Test readTest(@Valid @PathVariable("testId") Long id) {
        return testService.getTest(id);
    }
    @PutMapping
    void updateTest(@Valid @RequestBody TestRequestBody testRequestBody){
        testService.updateTest(testRequestBody);
    }
    @DeleteMapping(path = "{testId}")
    void deleteTest(@PathVariable("testId") Long id){
        testService.deleteTest(id);
    }
    @GetMapping
    List<Test> getTests(){
        return testService.getTests();
    }
}
