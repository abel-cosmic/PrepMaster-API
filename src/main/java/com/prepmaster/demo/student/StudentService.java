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
        log.info("Updating course {}", student.getId());
        extracted(studentRequestBody, student);
        studentRepository.save(student);
        log.info("Updated student {} successfully", student.getId());
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
    

    public void updateStudent(StudentRequestBody studentRequestBody) {
        Student student = studentRequestBody.getStudent();
        log.info("Updating course {}", student.getId());
        extracted(studentRequestBody, student);
        if (!studentRepository.existsById(student.getId())) {
            NotFoundException notFoundException = new NotFoundException("Course with ID " + student.getId() + " not found");
            log.error("error course {} not found could not update a non existing tuple", student.getId() , notFoundException);
            return;
        }
        studentRepository.save(student);
        log.info("Updated student {} successfully", student.getId());
    }

    private void extracted(StudentRequestBody studentRequestBody, Student student) {
        Department department = departmentService.getDepartment(studentRequestBody.getDepartmentId());
        student.setDepartment(department);
    }

    public void deleteStudent(Long id) {
        log.info("Deleting course {}", id);
        if (!studentRepository.existsById(id)) {
            NotFoundException notFoundException = new NotFoundException("Course with ID " + id + " not found");
            log.error("error course {} not found could not delete a non existing tuple", id , notFoundException);
            return;
        }
        studentRepository.deleteById(id);
        log.info("Deleted course {} successfully", id);
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
