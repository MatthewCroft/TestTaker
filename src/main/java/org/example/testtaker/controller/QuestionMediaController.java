package org.example.testtaker.controller;

import org.example.testtaker.dto.QuestionMediaUploadRequest;
import org.example.testtaker.model.QuestionMedia;
import org.example.testtaker.service.QuestionMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question-media")
public class QuestionMediaController {

    private QuestionMediaService questionMediaService;

    @Autowired
    public QuestionMediaController(QuestionMediaService questionMediaService) {
        this.questionMediaService = questionMediaService;
    }

    @PostMapping
    public ResponseEntity<QuestionMedia> createPresignedUploadUrl(@RequestBody QuestionMediaUploadRequest questionMediaUploadRequest) {
       if (questionMediaUploadRequest.getKey().isEmpty())  {
           return ResponseEntity
                   .badRequest()
                   .build();
       }

       QuestionMedia questionMedia = this.questionMediaService.createPresignedUrl(questionMediaUploadRequest);
       return ResponseEntity.ok(questionMedia);
    }
}
