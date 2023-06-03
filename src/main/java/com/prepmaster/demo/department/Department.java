package com.prepmaster.demo.department;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.prepmaster.demo.admin.Admin;
import com.prepmaster.demo.course.Course;
import com.prepmaster.demo.student.Student;
import com.prepmaster.demo.teacher.Teacher;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Department")
@Table(
        name = "department",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "department_name_unique",
                        columnNames = "name")
        }
)
public class Department {
    @Id
    @SequenceGenerator(
            name = "department_sequence",
            sequenceName = "department_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "department_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )

    private String name;
    @Column(
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;

    @ManyToOne(
            fetch = FetchType.LAZY //Why
    )
    @JoinColumn(
            name = "admin_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "admin_department_id_fk"
            )
    )
    private Admin admin;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY //Default is Eager for 1-1
            //when lazy this doesn't fetch the whole student object with it, go and mess with to string
    )
    @JoinColumn(
            name = "department_head_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "department_head_id_department_id_fk"
            )
    )
    private Teacher departmentHead;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, //DOC: makes courses heads if they don't exist
            mappedBy = "department"
            //DOC: fetch is lazy by default for 1-N relationships
            //DOC: orphan type is false by default so if this is deleted courses tied to this won't be
    )
    private List<Course> courses = new ArrayList<>();

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, //DOC: makes teachers heads if they don't exist
            mappedBy = "department"
            //DOC: fetch is lazy by default for 1-N relationships
            //DOC: orphan type is false by default so if this is deleted teachers tied to this won't be
    )
    private List<Teacher> teachers = new ArrayList<>();

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, //DOC: makes students heads if they don't exist
            mappedBy = "department"
            //DOC: fetch is lazy by default for 1-N relationships
            //DOC: orphan type is false by default so if this is deleted students tied to this won't be
    )
    private List<Student> students = new ArrayList<>();

    public Department() {
    }

    public Department(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Department(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @JsonIgnore
    public Admin getAdmin() {
        return admin;
    }
    @JsonProperty(value = "adminId")
    public Long getAdminId(){
        return admin.getId();
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    @JsonIgnore
    public Teacher getDepartmentHead() {
        return departmentHead;
    }
    @JsonProperty(value = "departmentHead")
    public Long getDepartmentHeadId(){
        return departmentHead.getId();
    }

    public void setDepartmentHead(Teacher departmentHead) {
        this.departmentHead = departmentHead;
    }
    @JsonIgnore
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    public void addStudent(Student student){
        if(!this.students.contains(student)){
            this.students.add(student);
            student.setDepartment(this);
        }
    }
    public void removeStudent(Student student){
        if(this.students.contains(student)){
            this.students.remove(student);
            student.setDepartment(null);
        }
    }
    @JsonIgnore
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    public void addCourse(Course course){
        if(!this.courses.contains(course)){
            this.courses.add(course);
            course.setDepartment(this);
        }
    }
    public void removeCourse(Course course){
        if(this.courses.contains(course)){
            this.courses.remove(course);
            course.setDepartment(null);
        }
    }
@JsonIgnore
    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void addTeacher(Teacher teacher){
        if(!this.teachers.contains(teacher)){
            this.teachers.add(teacher);
            teacher.setDepartment(this);
        }
    }
    public void removeTeacher(Teacher teacher){
        if(this.teachers.contains(teacher)){
            this.teachers.remove(teacher);
            teacher.setDepartment(null);
        }
    }
    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}

