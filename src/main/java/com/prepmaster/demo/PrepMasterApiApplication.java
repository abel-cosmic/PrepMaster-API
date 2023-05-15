package com.prepmaster.demo;

import com.github.javafaker.Faker;
import com.prepmaster.demo.admin.Admin;
import com.prepmaster.demo.admin.AdminRepository;
import com.prepmaster.demo.bundle.Bundle;
import com.prepmaster.demo.course.Course;
import com.prepmaster.demo.department.Department;
import com.prepmaster.demo.department.DepartmentRepository;
import com.prepmaster.demo.departmenthead.DepartmentHead;
import com.prepmaster.demo.departmenthead.DepartmentHeadRepository;
import com.prepmaster.demo.question.Choice;
import com.prepmaster.demo.question.Question;
import com.prepmaster.demo.student.Student;
import com.prepmaster.demo.teacher.Teacher;
import com.prepmaster.demo.test.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
			departmentHead.setDepartment(department);//cascade type all for department head in department

			Course course = makeCourse();
			department.addCourse(course);

			Teacher teacher = makeTeacher();
			department.addTeacher(teacher);

			Bundle bundle = makeBundle();
			bundle.setTeacher(teacher);
			bundle.setCourse(course);

			Question question = makeQuestion();
			question.addChoice(makeChoice('A'));
			question.addChoice(makeChoice('B'));
			question.addChoice(makeChoice('C'));
			question.addChoice(makeChoice('D'));
			bundle.addQuestion(question);

			Question question1 = makeQuestion();
			question1.addChoice(makeChoice('A'));
			question1.addChoice(makeChoice('B'));
			question1.addChoice(makeChoice('C'));
			question1.addChoice(makeChoice('D'));
			bundle.addQuestion(question1);

			Student student = makeStudent();
			department.addStudent(student);

			Test test = makeTest();
			test.setBundle(bundle);
			test.setStudent(student);
			bundle.addTest(test);

			teacher.addBundle(bundle);
			department.addStudent(makeStudent());

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
				faker.random().nextInt(2) == 0 ? "M" : "F",
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
	private Course makeCourse() {
		Faker faker = new Faker();
		return new Course(
				faker.educator().course(),
				faker.lorem().paragraph()
		);
	}
	private Teacher makeTeacher() {
		Faker faker = new Faker();
		return new Teacher(
				faker.name().firstName(),
				faker.name().lastName(),
				faker.internet().emailAddress(),
				faker.phoneNumber().cellPhone(),
				faker.random().nextInt(2) == 0 ? "M" : "F",
				faker.internet().password()
		);
	}
	private Student makeStudent() {
		Faker faker = new Faker();
		return new Student(
				faker.name().firstName(),
				faker.name().lastName(),
				faker.internet().emailAddress(),
				faker.phoneNumber().cellPhone(),
				faker.random().nextInt(2) == 0 ? "M" : "F",
				faker.internet().password()
		);
	}
	private Bundle makeBundle() {
		Faker faker = new Faker();
		return new Bundle(
				faker.educator().course() + " test " + faker.random().nextInt(10) + " ",
				faker.lorem().paragraph()
		);
	}

	private Question makeQuestion() {
		Faker faker = new Faker();
		char[] chars = {'A', 'B', 'C', 'D'};
		char answer = chars[faker.random().nextInt(4)];
		return new Question(
				faker.lorem().sentence(5),
				answer,
				faker.lorem().sentence(10),
				faker.lorem().word()
		);
	}

	private Choice makeChoice(char c) {
		Faker faker = new Faker();
		return new Choice(c + ". " + faker.lorem().sentence(10));
	}

	private Test makeTest(){
		Faker faker = new Faker();
		return new Test(faker.random().nextInt(3));
	}
}
