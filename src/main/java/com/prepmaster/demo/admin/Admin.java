package com.prepmaster.demo.admin;

import com.prepmaster.demo.department.Department;
import com.prepmaster.demo.student.Student;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Admin")
@Table(
        name = "admin",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "admin_email_unique",
                        columnNames = "email")
        }
)
public class Admin {
    @Id
    @SequenceGenerator(
            name = "admin_sequence",
            sequenceName = "admin_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "admin_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @OneToMany(
            mappedBy = "department",
            orphanRemoval = true,
            cascade =CascadeType.ALL
    )
    private List<Department> departments = new ArrayList<>();
    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;
    @Column(
            name = "organization",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String organization;
    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;

    public Admin() {
    }

    public Admin(
            String email,
            String organization,
            String password) {
        this.email = email;
        this.organization = organization;
        this.password = password;
    }

    public Admin(Long id, String email, String organization, String password) {
        this.id = id;
        this.email = email;
        this.organization = organization;
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPassword() {
        return password;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", organization='" + organization + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
