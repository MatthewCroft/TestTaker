package org.example.testtaker.controller;

import org.example.testtaker.dto.CreateTestRequest;
import org.example.testtaker.model.Test;
import org.example.testtaker.exceptions.UserNotFoundException;
import org.example.testtaker.service.TestService;
import org.example.testtaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/{userId}/test")
public class TestController {
    private UserService userService;
    private TestService testService;

    @Autowired
    public TestController(UserService userService, TestService testService) {
        this.userService = userService;
        this.testService = testService;
    }

    @PostMapping
    public ResponseEntity<Test> createTest(@PathVariable String userId, @RequestBody CreateTestRequest createTestRequest) throws UserNotFoundException {
        if (createTestRequest.getName() == null ||
                createTestRequest.getName().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        if (this.userService.getUser(userId) == null) {
            throw new UserNotFoundException(String.format("User with id %d was not found", userId));
        }

        Test test = this.testService.createTest(createTestRequest, userId);
        return ResponseEntity.ok(test);
    }
}
