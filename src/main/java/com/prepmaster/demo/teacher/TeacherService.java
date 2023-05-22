package com.prepmaster.demo.teacher;

import com.prepmaster.demo.department.Department;
import com.prepmaster.demo.department.DepartmentRepository;
import com.prepmaster.demo.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;// this could not be helped

    public Teacher getTeacher(Long id){
        log.info("Getting teacher {}", id);
        Teacher teacher = teacherRepository
                .findById(id)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Teacher with ID " + id + " not found");
                            log.error("error teacher {} not found", id , notFoundException);
                            return notFoundException;
                        }
                );
        log.info("Got teacher {}", teacher.getId());
        return teacher;
    }

    void createNewTeacher(TeacherRequestBody teacherRequestBody){
        Teacher teacher = teacherRequestBody.getTeacher();
        log.info("Creating teacher {}", teacher);
        Long departmentId = teacherRequestBody.getDepartmentId();
        Department department = departmentRepository.findById(departmentId) //TODO : change with service repo method
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Department with ID " + departmentId + " not found");
                            log.error("error department {} not found", departmentId , notFoundException);
                            return notFoundException;
                        }
                );
        teacher.setDepartment(department);
        teacherRepository.save(teacher);
        log.info("Created teacher {} successfully", teacher.getId());
    }

    void updateCourse(TeacherRequestBody teacherRequestBody){
        Teacher teacher = teacherRequestBody.getTeacher();
        log.info("Updating teacher {}", teacher.getId());
        Long departmentId = teacherRequestBody.getDepartmentId();
        Department department = departmentRepository.findById(departmentId) //TODO : change with service repo method
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Department with ID " + departmentId + " not found");
                            log.error("error department {} not found", departmentId , notFoundException);
                            return notFoundException;
                        }
                );
        teacher.setDepartment(department);
        if (!teacherRepository.existsById(teacher.getId())) {
            NotFoundException notFoundException = new NotFoundException("Teacher with ID " + teacher.getId() + " not found");
            log.error("error teacher {} not found could not update a non existing tuple", teacher.getId() , notFoundException);
            return;
        }
        teacherRepository.save(teacher);
        log.info("Updated teacher {} successfully", teacher.getId());
    }

    void deleteTeacher(Long id){
        log.info("Deleting teacher {}", id);
        if (!teacherRepository.existsById(id)) {
            NotFoundException notFoundException = new NotFoundException("Teacher with ID " + id + " not found");
            log.error("error teacher {} not found could not delete a non existing tuple", id , notFoundException);
            return;
        }
        teacherRepository.findById(id).ifPresent( // deletes the department if the teacher is department head
                t -> {
                    if (t.getDepartmentHead()) {
                        departmentRepository.deleteById(
                                t.getDepartmentId()
                        );
                    }
                }
        );
        teacherRepository.deleteById(id);
        log.info("Deleted teacher {} successfully", id);
    }

    List<Teacher> getTeachers(){
        log.info("Getting all teachers");
        List<Teacher> teachers = teacherRepository.findAll();
        log.info("Got all teachers");
        return teachers;
    }
}
