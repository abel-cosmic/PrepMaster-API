package com.prepmaster.demo;

import com.github.javafaker.Faker;
import com.prepmaster.demo.admin.Admin;
import com.prepmaster.demo.admin.AdminRepository;
import com.prepmaster.demo.department.Department;
import com.prepmaster.demo.department.DepartmentRepository;
import com.prepmaster.demo.departmenthead.DepartmentHead;
import com.prepmaster.demo.departmenthead.DepartmentHeadRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDateTime;
import java.util.List;

/*
* TODO: tasks before doing anything
*  1. create your own branch
*  2. create the entity/classes
*  3. use the N-tier Architecture: Service layer, DOA, Controller,
*     and  repository for each class.
*  4. after checking for errors we will host the API in renderer.
* */
@SpringBootApplication
public class PrepMasterApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrepMasterApiApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
		AdminRepository adminRepository,
		DepartmentHeadRepository departmentHeadRepository,
		DepartmentRepository departmentRepository
	) {
		return args -> {
			Admin admin = makeAdmin();

			DepartmentHead departmentHead = makeDepartmentHead();
			admin.addDepartmentHead(departmentHead);

			Department department = makeDepartment();
			department.setDepartmentHead(departmentHead);//cascade type all for department head in department

			admin.addDepartment(department);//Note that you cant just make a department without having an admin it will not work
			adminRepository.save(admin);

		};
	}
	private Admin makeAdmin(){
		Faker faker = new Faker();
		return  new Admin(faker.internet().emailAddress(), faker.company().name(), faker.internet().password());
	}
	private DepartmentHead makeDepartmentHead(){
		Faker faker = new Faker();
		return new DepartmentHead(
				faker.name().firstName(),
				faker.name().lastName(),
				faker.internet().emailAddress(),
				faker.phoneNumber().cellPhone(),
				faker.random().nextInt(2) == 0 ? "Male" : "Female",
				faker.internet().password()
		);
	}
	private Department makeDepartment(){
		Faker faker = new Faker();
		return new Department(
				faker.job().field(),
				faker.lorem().paragraph()
		);
	}
}
