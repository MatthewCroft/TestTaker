package org.example.testtaker.dto;

public class CreateQuestionRequest {
    String question;
    String media;

    public CreateQuestionRequest(String question, String media) {
        this.question = question;
        this.media = media;
    }

    public CreateQuestionRequest(){}

    public String getQuestion() {
        return question;
    }

    public String getMedia() {
        return media;
    }
}
