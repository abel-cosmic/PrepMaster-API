package com.prepmaster.demo.teacher;

import com.prepmaster.demo.bundle.Bundle;
import com.prepmaster.demo.department.Department;
import com.prepmaster.demo.student.Student;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;
@Entity(name = "Teacher")
@Table(
        name = "teacher",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "teacher_email_unique",
                        columnNames = "email")
        }
)
public class Teacher {
    @Id
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "teacher_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
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
    private String password;

    @ManyToOne(
            fetch = FetchType.LAZY //Why
    )
    @JoinColumn(
            name = "department_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "department_teacher_id_fk"
            )
    )
    private Department department;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, //DOC: makes students heads if they don't exist
            mappedBy = "teacher"
            //DOC: fetch is lazy by default for 1-N relationships
            //DOC: orphan type is false by default so if this is deleted students tied to this won't be
    )
    private List<Bundle> bundles = new ArrayList<>();
    public Teacher() {
    }

    public Teacher(
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

    public Teacher(
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Bundle> getBundles() {
        return bundles;
    }

    public void setBundles(List<Bundle> bundles) {
        this.bundles = bundles;
    }

    public void addBundle(Bundle bundle){
        if(!this.bundles.contains(bundle)){
            this.bundles.add(bundle);
            bundle.setTeacher(this);
        }
    }
    public void removeBundle(Bundle bundle){
        if(this.bundles.contains(bundle)){
            this.bundles.remove(bundle);
            bundle.setTeacher(null);
        }
    }
    @Override
    public String toString() {
        return "Teacher{" +
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
