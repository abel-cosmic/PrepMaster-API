package com.prepmaster.demo.choice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.prepmaster.demo.question.Question;
import com.prepmaster.demo.teacher.Teacher;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Choice")
@Table(name = "choice")
public class Choice {
    @Id
    @SequenceGenerator( //TODO this class should not have it's own primary key fix later
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

    @Column(
            name = "index",
            nullable = false,
            columnDefinition = "INT"
    )
    private int index;

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

    public Choice(String choiceText, int index) {
        this.choiceText = choiceText;
        this.index = index;
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
    @JsonIgnore
    public Question getQuestion() {
        return question;
    }
    @JsonProperty(value = "questionId")
    public Long getQuestionId(){
        return  question.getId();
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "id=" + id +
                ", choiceText='" + choiceText + '\'' +
                ", index=" + index +
                ", question=" + question +
                '}';
    }
}
