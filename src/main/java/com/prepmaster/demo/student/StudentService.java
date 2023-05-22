package com.prepmaster.demo.student;

import com.prepmaster.demo.course.Course;
import com.prepmaster.demo.department.Department;
import com.prepmaster.demo.department.DepartmentRepository;
import com.prepmaster.demo.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor //LOMBOK SEE VIDEO
@Slf4j // so we can use log variable
public class StudentService {
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    public void createNewStudent(StudentRequestBody studentRequestBody) {
        Student student = studentRequestBody.getStudent();
        log.info("Updating course {}", student.getId());
        Long departmentId = studentRequestBody.getDepartmentId();
        Department department = departmentRepository.findById(departmentId) //TODO : change with service repo method
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Department with ID " + departmentId + " not found");
                            log.error("error department {} not found", departmentId , notFoundException);
                            return notFoundException;
                        }
                );
        student.setDepartment(department);
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
        Long departmentId = studentRequestBody.getDepartmentId();
        Department department = departmentRepository.findById(departmentId) //TODO : change with service repo method
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Department with ID " + departmentId + " not found");
                            log.error("error department {} not found", departmentId , notFoundException);
                            return notFoundException;
                        }
                );
        student.setDepartment(department);
        if (!studentRepository.existsById(student.getId())) {
            NotFoundException notFoundException = new NotFoundException("Course with ID " + student.getId() + " not found");
            log.error("error course {} not found could not update a non existing tuple", student.getId() , notFoundException);
            return;
        }
        studentRepository.save(student);
        log.info("Updated student {} successfully", student.getId());
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
}
