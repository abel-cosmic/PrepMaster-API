package com.prepmaster.demo.question;

import com.prepmaster.demo.question.Question;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Choices")
@Table(name = "choices")
public class Choices {
    @Id
    @SequenceGenerator(
            name = "choices_sequence",
            sequenceName = "choices_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "choices_sequence"
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
    private String choiceText;


    public Choices() {
    }

    public Choices(String choiceText) {
        this.choiceText = choiceText;
    }

    public Choices(Long id, String choiceText) {
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

    @Override
    public String toString() {
        return "Choices{" +
                "id=" + id +
                ", choiceText='" + choiceText + '\'' +
                '}';
    }
}
