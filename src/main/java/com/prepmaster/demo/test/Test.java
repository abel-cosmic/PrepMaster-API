package com.prepmaster.demo.test;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.prepmaster.demo.bundle.Bundle;
import com.prepmaster.demo.questionanswer.QuestionAnswer;
import com.prepmaster.demo.student.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Test")
@Table(
        name = "test"

)
public class Test {
    @Id
    @SequenceGenerator(
            name = "test_sequence",
            sequenceName = "test_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "test_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @NotNull(message = "Score must not be NULL")
    @Column(
            name = "score",
            nullable = false,
            columnDefinition = "INT"
    )
    private int score;

    @NotNull(message = "Taken at must not be NULL")
    @Column(
            name = "taken_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime takenAt;

    @ManyToOne(
            fetch = FetchType.LAZY //Why
    )
    @JoinColumn(
            name = "bundle_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "bundle_test_id_fk"
            )
    )
    private Bundle bundle;

    @ManyToOne(
            fetch = FetchType.LAZY //Why            questionAnswers.add(questionAnswer);

    )
    @JoinColumn(
            name = "student_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "student_test_id_fk"
            )
    )
    private Student student;
    @OneToMany(
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
            mappedBy = "test"
    )
    private List<QuestionAnswer> questionAnswers = new ArrayList<>();

    public Test(int score) {
        this.score = score;
        this.takenAt = LocalDateTime.now();
    }

    public Test() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDateTime getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(LocalDateTime takenAt) {
        this.takenAt = takenAt;
    }

    @JsonIgnore
    public Bundle getBundle() {
        return bundle;
    }

    @JsonProperty("bundleId")
    public Long getBundleId() {
        return bundle.getId();
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
    @JsonIgnore
    public Student getStudent() {
        return student;
    }

    @JsonProperty("studentId")
    public Long getStudentId() {
        return student.getId();
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    @JsonIgnore
    public List<QuestionAnswer> getQuestionAnswers() {
        return questionAnswers;
    }

    public void addQuestionAnswer(QuestionAnswer questionAnswer) {
        if(!questionAnswers.contains(questionAnswer)){
        }
    }

    public void removeQuestionAnswers(QuestionAnswer questionAnswer) {
        questionAnswers.remove(questionAnswer);
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", score='" + score + '\'' +
                ", takenAt=" + takenAt +
                '}';
    }
}
