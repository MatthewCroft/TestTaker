package org.example.testtaker.controller;

import org.example.testtaker.dto.CreateTestRequest;
import org.example.testtaker.model.Test;
import org.example.testtaker.exceptions.UserNotFoundException;
import org.example.testtaker.service.TestService;
import org.example.testtaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private UserService userService;
    private TestService testService;

    @Autowired
    public TestController(UserService userService, TestService testService) {
        this.userService = userService;
        this.testService = testService;
    }

    @PostMapping
    public ResponseEntity<Test> createTest(@RequestBody CreateTestRequest createTestRequest) throws UserNotFoundException {
        if (createTestRequest.getCreatedBy() == null ||
                createTestRequest.getName() == null ||
                createTestRequest.getName().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        if (this.userService.getUser(createTestRequest.getCreatedBy()) == null) {
            throw new UserNotFoundException(String.format("User with id %d was not found", createTestRequest.getCreatedBy()));
        }

        Test test = this.testService.createTest(createTestRequest);
        return ResponseEntity.ok(test);
    }
}
