package org.example.testtaker.controller;

import org.example.testtaker.dto.CreateUserRequest;
import org.example.testtaker.model.User;
import org.example.testtaker.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserControllerTest {
    @Autowired
    UserController userController;

    @MockitoBean
    UserService userService;

//    @Test
//    public void testUserControllerReturnsBadRequestWhenNameIsEmpty() {
//       ResponseEntity responseEntity = userController.createUser(new CreateUserRequest(""));
//       assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//    }
//
//    @Test
//    public void testUserControllerReturnsSuccess() {
//        when(userService.createUser(any())).thenReturn(new User(1, "matt"));
//        ResponseEntity<User> response = userController.createUser(new CreateUserRequest("matt"));
//        assertEquals("matt", response.getBody().getName());
//    }
}
