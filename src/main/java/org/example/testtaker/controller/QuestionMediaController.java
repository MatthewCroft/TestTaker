package org.example.testtaker.controller;

import org.example.testtaker.dto.QuestionMediaUploadRequest;
import org.example.testtaker.exceptions.TestNotFoundException;
import org.example.testtaker.model.QuestionMedia;
import org.example.testtaker.service.QuestionMediaService;
import org.example.testtaker.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/test/{testId}/question-media")
public class QuestionMediaController {

    private QuestionMediaService questionMediaService;
    private TestService testService;

    @Autowired
    public QuestionMediaController(QuestionMediaService questionMediaService, TestService testService) {
        this.questionMediaService = questionMediaService;
        this.testService = testService;
    }

    @PostMapping
    public ResponseEntity<QuestionMedia> createPresignedUploadUrl(@RequestBody QuestionMediaUploadRequest questionMediaUploadRequest, @PathVariable Integer testId) throws Exception {
       if (questionMediaUploadRequest.getKey().isEmpty())  {
           return ResponseEntity
                   .badRequest()
                   .build();
       }

       if (this.testService.getTest(testId) == null) {
           throw new TestNotFoundException(String.format("Test with id %d was not found", testId));
       }

       QuestionMedia questionMedia = this.questionMediaService.createPresignedUrl(questionMediaUploadRequest, testId);
       return ResponseEntity.ok(questionMedia);
    }
}
