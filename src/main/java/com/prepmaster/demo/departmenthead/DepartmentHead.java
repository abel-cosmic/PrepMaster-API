package com.prepmaster.demo.departmenthead;

import com.prepmaster.demo.admin.Admin;
import com.prepmaster.demo.department.Department;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;
@Entity(name = "DepartmentHead")
@Table(
        name = "department_head",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "department_head_email_unique",
                        columnNames = "email")
        }
)
public class DepartmentHead {
    @Id
    @SequenceGenerator(
            name = "department_head_sequence",
            sequenceName = "department_head_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "department_head_sequence"
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
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "admin_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "admin_department_head_id_fk"
            )
    )
    private Admin admin;

    @OneToOne(
            cascade = CascadeType.ALL// SHOULD WE CREATE DEPARTMENT HEAD ALONG SIDE DEPARTMENT
    )
    @JoinColumn(
            name = "department_id",
            nullable= false, // what happens if the department head is deleted
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "department_department_head_id_fk"
            )
    )
    private Department department;
    public DepartmentHead() {
    }

    public DepartmentHead(
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

    public DepartmentHead(
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

    public DepartmentHead(String firstName, String lastName, String email, String phoneNumber, String gender, String password, Admin admin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.password = password;
        this.admin = admin;
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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "DepartmentHead{" +
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
