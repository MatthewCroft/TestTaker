package org.example.testtaker.service;

import org.example.testtaker.dto.CreateQuestionRequest;
import org.example.testtaker.entities.QuestionEntity;
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

    // may need to update this to get the presigned url for the download of the media
    public Question createQuestion(CreateQuestionRequest createQuestionRequest) {
        QuestionEntity questionEntity = new QuestionEntity(createQuestionRequest.getTestId(), createQuestionRequest.getQuestion(), createQuestionRequest.getMedia());
        QuestionEntity createdQuestion = this.questionRepository.save(questionEntity);
        return new Question(createdQuestion.getQuestionId(), createQuestionRequest.getTestId(), createQuestionRequest.getQuestion(), createQuestionRequest.getMedia());
    }
}
