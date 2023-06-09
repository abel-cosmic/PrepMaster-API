package com.prepmaster.demo;

import com.github.javafaker.Faker;
import com.prepmaster.demo.admin.Admin;
import com.prepmaster.demo.admin.AdminRepository;
import com.prepmaster.demo.bundle.Bundle;
import com.prepmaster.demo.choice.Choice;
import com.prepmaster.demo.course.Course;
import com.prepmaster.demo.course.CourseRepository;
import com.prepmaster.demo.department.Department;
import com.prepmaster.demo.department.DepartmentRepository;
import com.prepmaster.demo.question.Question;
import com.prepmaster.demo.questionanswer.QuestionAnswer;
import com.prepmaster.demo.questionanswer.QuestionAnswerID;
import com.prepmaster.demo.student.Student;
import com.prepmaster.demo.student.StudentRepository;
import com.prepmaster.demo.teacher.Teacher;
import com.prepmaster.demo.teacher.TeacherRepository;
import com.prepmaster.demo.test.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


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
		DepartmentRepository departmentRepository,
		StudentRepository studentRepository,
		TeacherRepository teacherRepository,
		CourseRepository courseRepository
	) {
		return args -> {
			Admin admin = makeAdmin();

			Department department = makeDepartment();
			admin.addDepartment(department);

			Teacher departmentHead = makeTeacher();
			departmentHead.setDepartmentHead(true);
			department.setDepartmentHead(departmentHead);
			department.addTeacher(departmentHead);

			Course course = makeCourse();
			department.addCourse(course);

			Teacher teacher = makeTeacher();
			department.addTeacher(teacher);

			Bundle bundle = makeBundle();
			bundle.setTeacher(teacher);
			bundle.setCourse(course);

			Question question = makeQuestion();
			question.addChoice(makeChoice('A', 0));
			question.addChoice(makeChoice('B', 1));
			question.addChoice(makeChoice('C', 2));
			question.addChoice(makeChoice('D', 3));
			bundle.addQuestion(question);
			Question question1 = makeQuestion();
			question1.addChoice(makeChoice('A', 0));
			question1.addChoice(makeChoice('B', 1));
			question1.addChoice(makeChoice('C', 2));
			question1.addChoice(makeChoice('D', 3));
			bundle.addQuestion(question1);
			Question question2 = makeQuestion();
			question2.addChoice(makeChoice('A', 0));
			question2.addChoice(makeChoice('B', 1));
			question2.addChoice(makeChoice('C', 2));
			question2.addChoice(makeChoice('D', 3));
			bundle.addQuestion(question2);

			Student student = makeStudent();
			department.addStudent(student);

			Test test = makeTest();
			test.setBundle(bundle);
			test.setStudent(student);

			QuestionAnswer questionAnswer = new QuestionAnswer(new QuestionAnswerID(test.getId(), question.getId()),0,test,question);
			QuestionAnswer questionAnswer1 = new QuestionAnswer(new QuestionAnswerID(test.getId(), question1.getId()),2,test,question1);
			QuestionAnswer questionAnswer2 = new QuestionAnswer(new QuestionAnswerID(test.getId(), question2.getId()),2,test,question2);
			question.addQuestionAnswer(questionAnswer);
			question1.addQuestionAnswer(questionAnswer1);
			question2.addQuestionAnswer(questionAnswer2);
			test.addQuestionAnswer(questionAnswer);
			test.addQuestionAnswer(questionAnswer1);
			test.addQuestionAnswer(questionAnswer2);

			bundle.addTest(test);
			teacher.addBundle(bundle);
			department.addStudent(makeStudent());

			admin.addDepartment(department);//Note that you cant just make a department without having an admin it will not work
			adminRepository.save(admin);

//			System.out.println(question);
//			System.out.println(question1);
//			question.getChoices().forEach(
//					System.out::println
//			);
//			Admin admin1 = adminRepository.findById(1L).get();
//			System.out.println(admin1);
//			admin1.setOrganization("Hope");
//			adminRepository.save(admin1);

//			adminRepository.findById(1L).ifPresent(
//					System.out::println
//			);
//			departmentRepository.findById(1L).ifPresent(
//					System.out::println
//			);
//			courseRepository.findById(1L).ifPresent(
//					c -> {
//						System.out.println(c.getDepartment());
//					}
//			);
//			departmentRepository.deleteById(1L);
//			adminRepository.deleteAdminById(1L);
//			teacherRepository.deleteById(2L);
//			adminRepository.findById(1L).ifPresent(
//					System.out::println
//			);
		};
	}
	private Admin makeAdmin(){
		Faker faker = new Faker();
		return  new Admin(faker.internet().emailAddress(), faker.company().name(), faker.internet().password());
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
				faker.internet().password(),
				false
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
				faker.lorem().paragraph(),
				faker.random().nextInt(10) // Generate a random integer for timeAllowed
		);
	}

	private Question makeQuestion() {
		Faker faker = new Faker();
		return new Question(
				faker.lorem().sentence(5),
				faker.random().nextInt(4),
				faker.lorem().sentence(10),
				faker.lorem().word()
		);
	}

	private Choice makeChoice(char c, int x) {
		Faker faker = new Faker();
		return new Choice(c + ". " + faker.lorem().sentence(10), x);
	}

	private Test makeTest(){
		Faker faker = new Faker();
		return new Test(faker.random().nextInt(3));
	}
}
