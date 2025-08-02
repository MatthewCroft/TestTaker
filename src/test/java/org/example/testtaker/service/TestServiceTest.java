package org.example.testtaker.service;

import org.example.testtaker.dto.CreateTestRequest;
import org.example.testtaker.entities.TestEntity;
import org.example.testtaker.exceptions.UserNotFoundException;
import org.example.testtaker.model.User;
import org.example.testtaker.repository.TestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestServiceTest {
    @Mock
    TestRepository testRepository;

    @Mock
    UserService userService;

    @InjectMocks
    TestService testService;

    @Test
    public void testCreateTestThrowsExceptionWhenUserNotFound() {
        String userId = "Matt";
        when(userService.getUser(userId)).thenReturn(null);
        Exception e = assertThrows(UserNotFoundException.class, () -> testService.createTest(new CreateTestRequest(), userId));
        assertEquals("user with id Matt was not found", e.getMessage());
    }

    @Test
    public void testCreateTestSavesCorrectly() throws Exception {
       String userId = "Matt";
       String testName = "first test";
       CreateTestRequest createTestRequest = new CreateTestRequest(testName);

       when(userService.getUser(userId)).thenReturn(new User(userId));
       when(testRepository.save(any())).thenReturn(new TestEntity(createTestRequest.getName(), userId));

       org.example.testtaker.model.Test test = testService.createTest(createTestRequest, userId);
       assertEquals(new org.example.testtaker.model.Test(null, testName, userId), test);
    }
}
