package com.prepmaster.demo.bundle;

import com.prepmaster.demo.course.Course;
import com.prepmaster.demo.teacher.Teacher;
import jakarta.persistence.*;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Bundle")
@Table(name = "bundle")
public class Bundle {
    @Id
    @SequenceGenerator(
            name = "bundle_sequence",
            sequenceName = "bundle_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "bundle_sequence"
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
    @ManyToOne
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "id"
    )
    private Course course;
    @ManyToOne
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "id"
    )
    private Teacher teacher;
    @Column(
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;
    @Column(
            name = "timeAllowed",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDate timeAllowed;

    public Bundle() {
    }

    public Bundle(
            String name,
            String description,
            LocalDate timeAllowed) {
        this.name = name;
        this.description = description;
        this.timeAllowed = timeAllowed;
    }

    public Bundle(
            Long id,
            String name,
            String description,
            LocalDate timeAllowed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.timeAllowed = timeAllowed;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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

    public LocalDate getTimeAllowed() {
        return timeAllowed;
    }

    public void setTimeAllowed(LocalDate timeAllowed) {
        this.timeAllowed = timeAllowed;
    }

    @Override
    public String toString() {
        return "Bundle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", timeAllowed=" + timeAllowed +
                '}';
    }
}
