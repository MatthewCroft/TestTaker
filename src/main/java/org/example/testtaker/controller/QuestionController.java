package org.example.testtaker.controller;

import org.example.testtaker.dto.CreateQuestionRequest;
import org.example.testtaker.exceptions.TestNotFoundException;
import org.example.testtaker.model.Question;
import org.example.testtaker.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test/{id}/question")
public class QuestionController {
    private TestService testService;
    private QuestionService questionService;

    public QuestionController(TestService testService, QuestionService questionService) {
       this.testService = testService;
       this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody CreateQuestionRequest createQuestionRequest) throws Exception {
        if (createQuestionRequest.getTestId() == null || createQuestionRequest.getQuestion().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        if (this.testService.getTest(createQuestionRequest.getTestId()) == null) {
            throw new TestNotFoundException(String.format("test with id %d not found", createQuestionRequest.getTestId()));
        }
    }
}
