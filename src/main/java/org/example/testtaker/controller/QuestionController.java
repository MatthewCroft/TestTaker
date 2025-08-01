package org.example.testtaker.controller;

import org.example.testtaker.dto.CreateQuestionRequest;
import org.example.testtaker.exceptions.TestNotFoundException;
import org.example.testtaker.model.Question;
import org.example.testtaker.service.QuestionService;
import org.example.testtaker.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/{userId}/test/{testId}/question")
public class QuestionController {
    private TestService testService;
    private QuestionService questionService;

    public QuestionController(TestService testService, QuestionService questionService) {
       this.testService = testService;
       this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@PathVariable String userId, @PathVariable Integer testId, @RequestBody CreateQuestionRequest createQuestionRequest) throws Exception {
        if (createQuestionRequest.getQuestion().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        if (this.testService.getTest(testId) == null) {
            throw new TestNotFoundException(String.format("test with id %d not found", testId));
        }

        Question question = this.questionService.createQuestion(createQuestionRequest, testId, userId);
        return ResponseEntity.ok(question);
    }
}
