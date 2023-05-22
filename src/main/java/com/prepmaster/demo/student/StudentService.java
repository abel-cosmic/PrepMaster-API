package com.prepmaster.demo.student;

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
    public void createNewStudent(Student student) {
        log.info("Creating student {}", student);
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
}
