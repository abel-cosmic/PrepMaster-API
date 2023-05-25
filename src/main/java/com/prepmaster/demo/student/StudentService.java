package com.prepmaster.demo.student;

import com.prepmaster.demo.department.Department;
import com.prepmaster.demo.department.DepartmentService;
import com.prepmaster.demo.exception.ApiRequestException;
import com.prepmaster.demo.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor //LOMBOK SEE VIDEO
@Slf4j // so we can use log variable
public class StudentService {
    private final StudentRepository studentRepository;
    private final DepartmentService departmentService;

    public void createNewStudent(StudentRequestBody studentRequestBody) {
        Student student = studentRequestBody.getStudent();
        log.info("Creating student {}", student);
        Department department = departmentService.getDepartment(studentRequestBody.getDepartmentId());
        student.setDepartment(department);
        studentRepository.save(student);
        log.info("Created student {} successfully", student);
    }

    public Student getStudent(Long id) {
        return studentRepository
                .findById(id)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("student with ID " + id + " not found");
                            log.error("error student {} not found", id , notFoundException);
                            return notFoundException;
                        }
                );
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public StudentStatistics getStatistics(Long id) {
        Optional<Integer> numberOfQuestionsSolved = studentRepository.getNumberOfQuestionsSolved(id);
        Optional<Integer> numberOfQuestionsAttempted = studentRepository.getNumberOfQuestionsAttempted(id);
        Optional<Integer> numberOfTestsTaken = studentRepository.getNumberOfTestsTaken(id);
        if (
                numberOfQuestionsSolved.isEmpty() ||
                        numberOfQuestionsAttempted.isEmpty() ||
                        numberOfTestsTaken.isEmpty()
        ) {
            ApiRequestException apiRequestException = new ApiRequestException("Unable to fetch dta");
            log.error("error fetching data for student {}", id, apiRequestException);
            throw apiRequestException;
        }
        return new StudentStatistics(
                numberOfQuestionsSolved.get(),
                numberOfQuestionsAttempted.get(),
                numberOfTestsTaken.get()
        );
    }
}
