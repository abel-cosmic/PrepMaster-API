package com.prepmaster.demo.question;

import com.prepmaster.demo.teacher.Teacher;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Choice")
@Table(name = "choice")
public class Choice {
    @Id
    @SequenceGenerator(
            name = "choice_sequence",
            sequenceName = "choice_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "choice_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "choice_text",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String choiceText;

    @ManyToOne(
            fetch = FetchType.LAZY //Why
    )
    @JoinColumn(
            name = "question_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "question_choice_id_fk"
            )
    )
    private Question question;

    public Choice() {
    }

    public Choice(String choiceText) {
        this.choiceText = choiceText;
    }

    public Choice(Long id, String choiceText) {
        this.id = id;
        this.choiceText = choiceText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChoiceText() {
        return choiceText;
    }

    public void setChoiceText(String choiceText) {
        this.choiceText = choiceText;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Choices{" +
                "id=" + id +
                ", choiceText='" + choiceText + '\'' +
                '}';
    }
}
