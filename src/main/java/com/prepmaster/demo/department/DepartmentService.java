package com.prepmaster.demo.department;

import com.prepmaster.demo.admin.Admin;
import com.prepmaster.demo.admin.AdminService;
import com.prepmaster.demo.exception.ApiRequestException;
import com.prepmaster.demo.exception.NotFoundException;
import com.prepmaster.demo.teacher.Teacher;
import com.prepmaster.demo.teacher.TeacherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor //LOMBOK SEE VIDEO
@Slf4j // so we can use log variable
public class DepartmentService {
    private  final DepartmentRepository departmentRepository;
    private final AdminService adminService;
    private final TeacherService teacherService;

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
        Admin admin = adminService.getAdmin(departmentRequestBody.getAdminId());
        Teacher departmentHead = departmentRequestBody.getDepartmentHead();
        department.setAdmin(admin);
        department.addTeacher(departmentHead);
        department.setDepartmentHead(departmentHead);
        departmentRepository.save(department);
        log.info("Created department {} successfully", department.getId());
    }
    public void updateDepartment(DepartmentRequestBodyUpdate departmentRequestBody) {
        Department department = departmentRequestBody.getDepartment();
        log.info("Creating department {}", department);
        Admin admin = adminService.getAdmin(departmentRequestBody.getAdminId());
        Teacher departmentHead = teacherService.getTeacher( departmentRequestBody.getDepartmentHeadId());
        department.setAdmin(admin);
        department.setDepartmentHead(departmentHead);
        department.addTeacher(departmentHead);
        departmentRepository.save(department);
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

    public DepartmentStatistics getStatistics(Long id) {
        log.info("Getting department {} statistics", id);
        Optional<Integer> numberOfStudents = departmentRepository.getNumberOfStudents(id);
        Optional<Integer> numberOfTeachers = departmentRepository.getNumberOfTeachers(id);
        Optional<Integer> numberOfCourse = departmentRepository.getNumberOfCourses(id);
        Optional<Integer> numberOfBundles = departmentRepository.getNumberOfBundles(id);
        Optional<Integer> numberOfQuestions = departmentRepository.getNumberOfQuestions(id);
        Optional<Integer> numberOfTests = departmentRepository.getNumberOfTests(id);
        if (
                numberOfStudents.isEmpty() ||
                        numberOfTeachers.isEmpty() ||
                        numberOfCourse.isEmpty() ||
                        numberOfBundles.isEmpty() ||
                        numberOfQuestions.isEmpty() ||
                        numberOfTests.isEmpty()
        ) {
            ApiRequestException apiRequestException = new ApiRequestException("Unable to fetch dta");
            log.error("error fetching data for department {}", id, apiRequestException);
            throw apiRequestException;
        }
        log.info("Got department {} statistics", id);
        return new DepartmentStatistics(
                numberOfStudents.get(),
                numberOfTeachers.get(),
                numberOfCourse.get(),
                numberOfBundles.get(),
                numberOfQuestions.get(),
                numberOfTests.get()
        );
    }
}
