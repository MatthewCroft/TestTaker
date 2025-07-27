package org.example.testtaker.dto;

public class CreateQuestionRequest {
    Integer testId;
    String question;
    String media;

    public CreateQuestionRequest(Integer testId, String question, String media) {
        this.testId = testId;
        this.question = question;
        this.media = media;
    }

    public CreateQuestionRequest(){}

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
