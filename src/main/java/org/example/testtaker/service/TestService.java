package org.example.testtaker.service;

import org.example.testtaker.dto.CreateTestRequest;
import org.example.testtaker.model.Test;
import org.example.testtaker.model.User;
import org.example.testtaker.entities.TestEntity;
import org.example.testtaker.repository.TestRepository;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private TestRepository testRepository;
    private UserService userService;

    public TestService(TestRepository testRepository, UserService userService) {
        this.testRepository = testRepository;
        this.userService = userService;
    }

    public Test createTest(CreateTestRequest createTestRequest, Integer userId) {
        TestEntity testEntity = new TestEntity(createTestRequest.getName(), userId);
        TestEntity createdTest = this.testRepository.save(testEntity);
        User user = this.userService.getUser(createdTest.getCreatedBy());
        return new Test(createdTest.getTestId(), createdTest.getName(), user.getName());
    }

    public Test getTest(Integer testId) {
        TestEntity testEntity = this.testRepository.findById(testId).orElse(null);
        if (testEntity == null) return null;
        User user = this.userService.getUser(testEntity.getCreatedBy());
        return new Test(testEntity.getTestId(), testEntity.getName(), user.getName());
    }
}
