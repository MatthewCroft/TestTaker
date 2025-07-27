package org.example.testtaker.controller;

import org.example.testtaker.dto.CreateUserRequest;
import org.example.testtaker.model.User;
import org.example.testtaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
       this.userService = userService;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest user) {
        if (user.getName().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        User createdUser = userService.createUser(user);

        return ResponseEntity.ok(createdUser);
    }
}
