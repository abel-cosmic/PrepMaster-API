package com.prepmaster.demo.department;

import com.prepmaster.demo.admin.Admin;
import com.prepmaster.demo.admin.AdminRepository;
import com.prepmaster.demo.course.Course;
import com.prepmaster.demo.exception.NotFoundException;
import com.prepmaster.demo.student.Student;
import com.prepmaster.demo.teacher.Teacher;
import com.prepmaster.demo.teacher.TeacherRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor //LOMBOK SEE VIDEO
@Slf4j // so we can use log variable
public class DepartmentService {
    private final AdminRepository adminRepository;
    private  final DepartmentRepository departmentRepository;
    private final TeacherRepository teacherRepository;

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }
    public Department getDepartment(Long id) {
        return departmentRepository
                .findById(id)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Department with ID " + id + " not found");
                            log.error("error department {} not found", id , notFoundException);
                            return notFoundException;
                        }
                );
    }
    public void createNewDepartment(DepartmentRequestBody departmentRequestBody) {
        Department department = departmentRequestBody.getDepartment();
        log.info("Creating department {}", department);
        Long adminId = departmentRequestBody.getAdminId();
        Long departmentHeadId = departmentRequestBody.getDepartmentHeadId();
        Admin admin = adminRepository.findById(adminId) //TODO : change with service repo method
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("admin with ID " + adminId + " not found");
                            log.error("error admin {} not found", adminId , notFoundException);
                            return notFoundException;
                        }
                );
        Teacher teacher = teacherRepository.findById(departmentHeadId)
                .orElseThrow(
                        ()->{
                            NotFoundException notFoundException = new NotFoundException("department with ID " + departmentHeadId + " not found");
                            log.error("error department {} not found", departmentHeadId , notFoundException);
                            return notFoundException;
                        }
                );
        department.setAdmin(admin);
        department.setDepartmentHead(teacher);
        departmentRepository.save(department);
        log.info("Created department {} successfully", department.getId());
    }
    public void updateDepartment(DepartmentRequestBody departmentRequestBody) {
        Department department = departmentRequestBody.getDepartment();
        log.info("Creating department {}", department);
        Long adminId = departmentRequestBody.getAdminId();
        Long departmentHeadId = departmentRequestBody.getDepartmentHeadId();
        Admin admin = adminRepository.findById(adminId) //TODO : change with service repo method
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("admin with ID " + adminId + " not found");
                            log.error("error admin {} not found", adminId , notFoundException);
                            return notFoundException;
                        }
                );
        Teacher teacher = teacherRepository.findById(departmentHeadId)
                .orElseThrow(
                        ()->{
                            NotFoundException notFoundException = new NotFoundException("department with ID " + departmentHeadId + " not found");
                            log.error("error department {} not found", departmentHeadId , notFoundException);
                            return notFoundException;
                        }
                );
        department.setAdmin(admin);
        department.setDepartmentHead(teacher);
        if (!departmentRepository.existsById(department.getId())) {
            NotFoundException notFoundException = new NotFoundException("department with ID " + department.getId() + " not found");
            log.error("error department {} not found could not update a non existing tuple", department.getId() , notFoundException);
            return;
        }
        departmentRepository.save(department);
        log.info("Updated course {} successfully", department.getId());
    }

    public void deleteDepartment(Long id) {
        log.info("Deleting department {}", id);
        if (!departmentRepository.existsById(id)) {
            NotFoundException notFoundException = new NotFoundException("department with ID " + id + " not found");
            log.error("error department {} not found could not delete a non existing tuple", id , notFoundException);
            return;
        }
        departmentRepository.deleteById(id);
        log.info("Deleted department {} successfully", id);
    }
}
