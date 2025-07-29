package org.example.testtaker.dto;

public class QuestionMediaUploadRequest {
    String key;

    public QuestionMediaUploadRequest(String key) {
        this.key = key;
    }

    public QuestionMediaUploadRequest(){}

    public String getKey() {
        return key;
    }
}
