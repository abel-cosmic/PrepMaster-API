package com.prepmaster.demo.question;

import com.prepmaster.demo.bundle.Bundle;
import com.prepmaster.demo.questionanswer.QuestionAnswer;
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
            columnDefinition = "INT"
    )
    private int answerIndex;
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
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
            mappedBy = "question"
    )
    private List<QuestionAnswer> questionAnswers = new ArrayList<>();
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, //DOC: makes choices if they don't exist
            mappedBy = "question",
            fetch = FetchType.EAGER// so that the choices come with the question
            //DOC: fetch is lazy by default for 1-N relationships
            //DOC: an orphan type is false by default, so if this is deleted students tied to this won't be
    )
    private List<Choice> choices = new ArrayList<>();

    public Question() {
    }

    public Question(
            String question,
//            List<Choice> choices,
            int answerIndex,
            String explanation,
            String difficulty
            ) {
        this.question = question;
//        this.choices = choices;
        this.answerIndex = answerIndex;
        this.explanation = explanation;
        this.difficulty = difficulty;
    }

    public Question(
            Long id,
            String question,
            int answerIndex,
            String explanation,
            String difficulty) {
        this.id = id;
        this.question = question;
        this.answerIndex = answerIndex;
        this.explanation = explanation;
        this.difficulty = difficulty;
    }

    public List<QuestionAnswer> getQuestionAnswers() {
        return questionAnswers;
    }
    public void addQuestionAnswer(QuestionAnswer questionAnswer){
        if(!questionAnswers.contains(questionAnswer)){
            questionAnswers.add(questionAnswer);
        }
    }
    public void removeQuestionAnswer(QuestionAnswer questionAnswer){
            questionAnswers.remove(questionAnswer);
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

    public int getAnswerIndex() {
        return answerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        this.answerIndex = answerIndex;
    }

    public void setQuestionAnswers(List<QuestionAnswer> questionAnswers) {
        this.questionAnswers = questionAnswers;
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
                ", answer=" + answerIndex +
                ", explanation='" + explanation + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", choices=" + choices +
                '}';
    }
}
