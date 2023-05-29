package com.prepmaster.demo.bundle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.prepmaster.demo.course.Course;
import com.prepmaster.demo.question.Question;
import com.prepmaster.demo.teacher.Teacher;
import com.prepmaster.demo.test.Test;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @NotBlank(message = "Name must not be empty")
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;
    @NotBlank(message = "Description must not be empty")
    @Column(
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;
    @NotNull(message = "Time allowed must not be NULL")
    @Column(
            name = "timeAllowed",
            nullable = false,
            columnDefinition = "INT"
    )
    private int timeAllowed; //TODO CHANGE DATA TYPE

    @ManyToOne(
            fetch = FetchType.LAZY //Why
    )
    @JoinColumn(
            name = "teacher_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "teacher_bundle_id_fk"
            )
    )
    private Teacher teacher;

    @ManyToOne(
            fetch = FetchType.LAZY //Why
    )
    @JoinColumn(
            name = "course_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "course_bundle_id_fk"
            )
    )
    private Course course;

    @OneToMany(// so it deleted all without orphan removal weird
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, //DOC: makes students heads if they don't exist
            mappedBy = "bundle",
            fetch = FetchType.EAGER// so that the questions come with the bunndle
            //DOC: fetch is lazy by default for 1-N relationships
            //DOC: orphan type is false by default so if this is deleted students tied to this won't be
    )
    private List<Question> questions = new ArrayList<>();

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, //DOC: makes students heads if they don't exist
            mappedBy = "bundle",
            fetch = FetchType.EAGER// so that the questions come with the bunndle
            //DOC: fetch is lazy by default for 1-N relationships
            //DOC: orphan type is false by default so if this is deleted students tied to this won't be
    )
    private List<Test> tests = new ArrayList<>();

    public Bundle() {
    }

    public Bundle(
            String name,
            String description,
            int timeAllowed
    ) {
        this.name = name;
        this.description = description;
        this.timeAllowed = timeAllowed;
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

    public int getTimeAllowed() {
        return timeAllowed;
    }

    public void setTimeAllowed(int timeAllowed) {
        this.timeAllowed = timeAllowed;
    }

    @JsonIgnore
    public Teacher getTeacher() {
        return teacher;
    }

    @JsonProperty("teacherId")
    public Long getTeacherId() {
        return teacher.getId();
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @JsonIgnore
    public Course getCourse() {
        return course;
    }

    @JsonProperty("courseId")
    public Long getCourseId() {
        return course.getId();
    }

    public void setCourse(Course course) {
        this.course = course;
    }

//    @JsonIgnore
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question){
        if(!this.questions.contains(question)){
            this.questions.add(question);
            question.setBundle(this);
        }
    }
    public void removeQuestion(Question question){
        if(this.questions.contains(question)){
            this.questions.remove(question);
            question.setBundle(null);
        }
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
            test.setBundle(this);
        }
    }
    public void removeTest(Test test){
        if(this.tests.contains(test)){
            this.tests.remove(test);
            test.setBundle(null);
        }
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
