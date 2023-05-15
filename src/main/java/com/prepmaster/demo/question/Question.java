package com.prepmaster.demo.question;

import com.prepmaster.demo.bundle.Bundle;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Question")
@Table(name = "question")
public class Question {
    @Id
    @SequenceGenerator(
            name = "question_sequence",
            sequenceName = "question_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "question_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "question",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String question;
    @Column(
            name = "answer",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private char answer;
    @Column(
            name = "explanation",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String explanation;
    @Column(
            name = "difficulty",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String difficulty;

    @ManyToOne(
            fetch = FetchType.LAZY //Why
    )
    @JoinColumn(
            name = "bundle_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "bundle_question_id_fk"
            )
    )
    private Bundle bundle;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, //DOC: makes students heads if they don't exist
            mappedBy = "question",
            fetch = FetchType.EAGER// so that the choices come with the question
            //DOC: fetch is lazy by default for 1-N relationships
            //DOC: orphan type is false by default so if this is deleted students tied to this won't be
    )
    private List<Choice> choices = new ArrayList<>();

    public Question() {
    }

    public Question(
            String question,
//            List<Choice> choices,
            char answer,
            String explanation,
            String difficulty) {
        this.question = question;
//        this.choices = choices;
        this.answer = answer;
        this.explanation = explanation;
        this.difficulty = difficulty;
    }

    public Question(
            Long id,
            String question,
//            List<Choice> choices,
            char answer,
            String explanation,
            String difficulty) {
        this.id = id;
        this.question = question;
//        this.choices = choices;
        this.answer = answer;
        this.explanation = explanation;
        this.difficulty = difficulty;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public char getAnswer() {
        return answer;
    }

    public void setAnswer(char answer) {
        this.answer = answer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
    public void addChoice(Choice choice){
        if(!this.choices.contains(choice)){
            this.choices.add(choice);
            choice.setQuestion(this);
        }
    }
    public void removeChoice(Choice choice){
        if(this.choices.contains(choice)){
            this.choices.remove(choice);
            choice.setQuestion(null);
        }
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
//                ", choices=" + choices +
                ", answer=" + answer +
                ", explanation='" + explanation + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }
}
