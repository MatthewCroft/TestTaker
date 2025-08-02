package org.example.testtaker.service;

import org.example.testtaker.dto.CreateTestRequest;
import org.example.testtaker.exceptions.UserNotFoundException;
import org.example.testtaker.model.Test;
import org.example.testtaker.model.User;
import org.example.testtaker.entities.TestEntity;
import org.example.testtaker.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Predicate;

@Service
public class TestService {
    private TestRepository testRepository;
    private UserService userService;

    public TestService(TestRepository testRepository, UserService userService) {
        this.testRepository = testRepository;
        this.userService = userService;
    }

    public Test createTest(CreateTestRequest createTestRequest, String userId) throws Exception {
        User user = this.userService.getUser(userId);
        Predicate<User> isNotExistingUser = Objects::isNull;
        if (isNotExistingUser.test(user)) {
            throw new UserNotFoundException(String.format("user with id %s was not found", userId));
        }

        TestEntity testEntity = new TestEntity(createTestRequest.getName(), userId);
        TestEntity createdTest = this.testRepository.save(testEntity);
        return new Test(createdTest.getTestId(), createdTest.getName(), createdTest.getCreatedBy());
    }

    public Test getTest(Integer testId) {
        TestEntity testEntity = this.testRepository.findById(testId).orElse(null);
        if (testEntity == null) return null;
        User user = this.userService.getUser(testEntity.getCreatedBy());
        return new Test(testEntity.getTestId(), testEntity.getName(), user.getName());
    }
}
