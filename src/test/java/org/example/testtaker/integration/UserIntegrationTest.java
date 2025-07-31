package org.example.testtaker.integration;

import org.example.testtaker.controller.UserController;
import org.example.testtaker.dto.CreateUserRequest;
import org.example.testtaker.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserIntegrationTest {

    @Autowired
    UserController userController;

    @Test
    public void createUser() {
        ResponseEntity<User> response = userController.createUser(new CreateUserRequest("Matt"));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Matt", response.getBody().getName());
    }
}
