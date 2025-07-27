package org.example.testtaker.service;

import org.example.testtaker.dto.CreateQuestionRequest;
import org.example.testtaker.model.Question;
import org.example.testtaker.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;
    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    public Question createQuestion(CreateQuestionRequest createQuestionRequest) {

    }
}
