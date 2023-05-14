package com.prepmaster.demo.test;


import jakarta.persistence.*;

import java.time.LocalDateTime;

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

    @Column(
            name = "score",
            nullable = false,
            columnDefinition = "INT"
    )
    private String score;

    @Column(
            name = "taken_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime takenAt;

    public Test(Long id, String score, LocalDateTime takenAt) {
        this.id = id;
        this.score = score;
        this.takenAt = takenAt;
    }

    public Test() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public LocalDateTime getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(LocalDateTime takenAt) {
        this.takenAt = takenAt;
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
