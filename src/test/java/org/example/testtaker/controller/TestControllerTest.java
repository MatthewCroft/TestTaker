package org.example.testtaker.controller;

import org.example.testtaker.dto.CreateTestRequest;
import org.example.testtaker.exceptions.UserNotFoundException;
import org.example.testtaker.service.TestService;
import org.example.testtaker.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestControllerTest {
    @Autowired
    TestController testController;

    @MockitoBean
    UserService userService;

    @MockitoBean
    TestService testService;

    @Test
    public void testReturnBadRequestWhenTestNameIsEmpty() throws Exception {
        ResponseEntity response = testController.createTest(2, new CreateTestRequest(""));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testReturnNotFoundWhenUserIdDoesNotExist() throws Exception {
        when(userService.getUser(6)).thenReturn(null);
        try {
            testController.createTest(1, new CreateTestRequest("matt"));
        } catch (UserNotFoundException e) {
            assertEquals("User with id 1 was not found", e.getMessage());
        }
    }
}
