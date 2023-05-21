package com.prepmaster.demo.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.prepmaster.demo.admin.Admin;
import com.prepmaster.demo.department.Department;
import com.prepmaster.demo.test.Test;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Student")
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "student_email_unique",
                        columnNames = "email")
        }
)
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
//    @ManyToOne
//    @JoinColumn(
//            name = "department_id",
//            referencedColumnName = "id"
//    )
//    private Department department;
    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;
    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;
    @Column(
            name = "phone_number",
            nullable = false,
            columnDefinition = "TEXT"
    )

    private String phoneNumber;
    @Column(
            name = "gender",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String gender;
    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotBlank(message="password must not be empty")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToOne(
            fetch = FetchType.LAZY //Why
    )
    @JoinColumn(
            name = "department_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "department_student_id_fk"
            )
    )
    private Department department;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, //DOC: makes students heads if they don't exist
            mappedBy = "student",
            fetch = FetchType.EAGER// so that the questions come with the bunndle
            //DOC: fetch is lazy by default for 1-N relationships
            //DOC: orphan type is false by default so if this is deleted students tied to this won't be
    )
    private List<Test> tests = new ArrayList<>();

    public Student() {
    }

    public Student(
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String gender,
            String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.password = password;
    }

    public Student(
            Long id,
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String gender,
            String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @JsonIgnore
    public Department getDepartment() {
        return department;
    }
    @JsonProperty(value = "departmentId")
    public Long getDepartmentId(){
        return department.getId();
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @JsonIgnore
    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public void addTest(Test test){
        if(!this.tests.contains(test)){
            this.tests.add(test);
            test.setStudent(this);
        }
    }
    public void removeTest(Test test){
        if(this.tests.contains(test)){
            this.tests.remove(test);
            test.setStudent(null);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
