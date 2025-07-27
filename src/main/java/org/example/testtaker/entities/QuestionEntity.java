package org.example.testtaker.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "question")
public class QuestionEntity {
    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer questionId;

    @Column(name="test_id")
    Integer testId;

    String question;

    String media;

    public QuestionEntity(Integer testId, String question, String media) {
       this.testId = testId;
       this.question = question;
       this.media = media;
    }

    public QuestionEntity() {}

    public Integer getQuestionId() {
        return questionId;
    }

    public Integer getTestId() {
        return testId;
    }

    public String getQuestion() {
        return question;
    }

    public String getMedia() {
        return media;
    }
}
