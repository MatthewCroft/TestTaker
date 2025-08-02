package org.example.testtaker.controller;

import org.apache.logging.log4j.util.Strings;
import org.example.testtaker.dto.CreateTestRequest;
import org.example.testtaker.model.Test;
import org.example.testtaker.exceptions.UserNotFoundException;
import org.example.testtaker.service.TestService;
import org.example.testtaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.function.Predicate;

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

    Predicate<String> isNameEmptyOrNull = (String s) -> Objects.isNull(s) || Strings.isEmpty(s);

    @PostMapping
    public ResponseEntity<Test> createTest(@PathVariable String userId, @RequestBody CreateTestRequest createTestRequest) throws Exception {
        if (isNameEmptyOrNull.test(createTestRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        Test test = this.testService.createTest(createTestRequest, userId);
        return ResponseEntity.ok(test);
    }
}
