package com.prepmaster.demo.admin;

import com.prepmaster.demo.department.Department;
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
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, //DOC: makes department heads if they don't exist
            mappedBy = "admin"
            //DOC: fetch is lazy by default for 1-N relationships
            //DOC: orphan type is false by default so if this is deleted Department heads tied to this won't be
    )
    private List<Department> departments = new ArrayList<>();

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

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public void addDepartment(Department department){
        if (!departments.contains(department) ){
            this.departments.add(department);
            department.setAdmin(this);
        }
    }

    public void removeDepartment(Department department){
        if (departments.contains(department) ){
            this.departments.remove(department);
            department.setAdmin(null);
        }
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
