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
    void createStudent(@Valid @RequestBody Student student){
        studentService.createNewStudent(student);
    }
    @GetMapping(path="{studentId}")
    Student readStudent(@Valid @PathVariable("studentId")Long id){
        return  studentService.getStudent(id);
    }
    @GetMapping
    List<Student> getStudents(){
        return studentService.getStudents();
    }
//    @P
//    void updateStudent(@Valid @RequestBody Student student){
//        studentService.updateStudent(student);
//    }
}
