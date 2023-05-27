package com.prepmaster.demo.admin;

import com.prepmaster.demo.exception.ApiRequestException;
import com.prepmaster.demo.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor //LOMBOK SEE VIDEO
@Slf4j // so we can use log variable
public class AdminService {
    private final AdminRepository adminRepository;

    public Admin getAdmin(Long id){
        log.info("Getting admin {}", id);
        Admin admin = adminRepository
                .findById(id)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Admin with ID " + id + " not found");
                            log.error("error admin {} not found", id , notFoundException);
                            return notFoundException;
                        }
                );
        log.info("Got admin {}", id);
        return admin;
    }
    void createNewAdmin(Admin admin){
        log.info("Creating admin {}", admin);
        adminRepository.save(admin);
        log.info("Created admin {} successfully", admin.getId());
    }

    void updateAdmin(Admin admin){
        log.info("Updating admin {}", admin.getId());
        if (!adminRepository.existsById(admin.getId())) {
            NotFoundException notFoundException = new NotFoundException("Admin with ID " + admin.getId() + " not found");
            log.error("error admin {} not found could not update a non existing tuple", admin.getId() , notFoundException);
            return;
        }
        adminRepository.save(admin);
        log.info("Updated admin {} successfully", admin.getId());
    }

    void deleteAdmin(Long id){
        log.info("Deleting admin {}", id);
        if (!adminRepository.existsById(id)) {
            NotFoundException notFoundException = new NotFoundException("Admin with ID " + id + " not found");
            log.error("error admin {} not found could not delete a non existing tuple", id , notFoundException);
            return;
        }
        adminRepository.deleteById(id); //this is the default it orphan_removal true
        log.info("Deleted admin {} successfully", id);
    }

    AdminStatistics getStatistics(long id) {
        Optional<Integer> numberOfStudents = adminRepository.getNumberOfStudents();
        Optional<Integer> numberOfTeachers = adminRepository.getNumberOfTeachers();
        Optional<Integer> numberOfCourses = adminRepository.getNumberOfCourses();
        Optional<Integer> numberOfDepartments = adminRepository.getNumberOfDepartments();
        if (
                numberOfStudents.isEmpty() ||
                        numberOfTeachers.isEmpty() ||
                        numberOfCourses.isEmpty() ||
                        numberOfDepartments.isEmpty()
        ) {
            ApiRequestException apiRequestException = new ApiRequestException("Unable to fetch dta");
            log.error("error fetching data for student {}", id, apiRequestException);
            throw apiRequestException;
        }
        return new AdminStatistics(
                numberOfStudents.get(),
                numberOfTeachers.get(),
                numberOfCourses.get(),
                numberOfDepartments.get()
        );
    }
}
