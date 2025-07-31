package org.example.testtaker.service;

import org.example.testtaker.dto.CreateQuestionRequest;
import org.example.testtaker.entities.QuestionEntity;
import org.example.testtaker.exceptions.TestNotFoundException;
import org.example.testtaker.exceptions.UserNotFoundException;
import org.example.testtaker.exceptions.UserNotTestCreatorException;
import org.example.testtaker.model.Question;
import org.example.testtaker.model.Test;
import org.example.testtaker.model.User;
import org.example.testtaker.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;
    private UserService userService;
    private TestService testService;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, TestService testService, UserService userService) {
        this.questionRepository = questionRepository;
        this.userService = userService;
        this.testService = testService;
    }

    // may need to update this to get the presigned url for the download of the media
    public Question createQuestion(CreateQuestionRequest createQuestionRequest, Integer testId, String userId) throws Exception {
        Test test = this.testService.getTest(testId);
        if (test == null) {
            throw new TestNotFoundException(String.format("test with id %d not found", testId));
        }

        User user = this.userService.getUser(userId);
        if (user == null) {
            throw new UserNotFoundException(String.format("user not found with id %s", userId));
        }

        if (!test.getCreatedBy().equals(user.getName())) {
            throw new UserNotTestCreatorException(String.format("user %s is not the creator of the test, cannot create questions", userId));
        }

        QuestionEntity questionEntity = new QuestionEntity(testId, createQuestionRequest.getQuestion(), createQuestionRequest.getMedia());
        QuestionEntity createdQuestion = this.questionRepository.save(questionEntity);

        return new Question(createdQuestion.getQuestionId(), createdQuestion.getTestId(), createQuestionRequest.getQuestion(), createQuestionRequest.getMedia());
    }
}
