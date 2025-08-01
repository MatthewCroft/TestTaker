package org.example.testtaker.model;

public class Question {
    Integer questionId;
    Integer testId;
    String question;
    String media;

    public Question(Integer questionId, Integer testId, String question, String media) {
        this.questionId = questionId;
        this.testId = testId;
        this.question = question;
        this.media = media;
    }

    public Question() {}

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

    @Override
    public boolean equals(Object t) {
        if (t == null) return false;
        if (t == this) {
            return true;
        }

        Question q = (Question) t;
        return q.getTestId().equals(this.getTestId()) &
                q.getQuestion().equals(this.getQuestion()) &
                q.getMedia().equals(this.getMedia());
    }
}
