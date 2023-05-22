package com.prepmaster.demo.student;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/api/students")
@AllArgsConstructor// creates a constructor, so we don't have too (Lombok)
@RestController// Allows us to send http requests into it
public class StudentController {
    private StudentService studentService;
    @PostMapping
    void createStudent(@Valid @RequestBody StudentRequestBody studentRequestBody){
        studentService.createNewStudent(studentRequestBody);
    }
    @GetMapping(path="{studentId}")
    Student readStudent(@Valid @PathVariable("studentId")Long id){
        return  studentService.getStudent(id);
    }
    @GetMapping
    List<Student> getStudents(){
        return studentService.getStudents();
    }

//    void deleteQuestionAnswer(
//            @Valid @PathVariable("questionId") Long questionId,
//            @Valid  @PathVariable("testId") Long testId
//    ) {
//        questionAnswerService.deleteQuestionAnswer(new QuestionAnswerID(testId,questionId));
//    }
//    @P
//    void updateStudent(@Valid @RequestBody Student student){
//        studentService.updateStudent(student);
//    }
}
