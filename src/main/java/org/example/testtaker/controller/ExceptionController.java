package org.example.testtaker.controller;

import org.example.testtaker.dto.ExceptionMessage;
import org.example.testtaker.exceptions.TestNotFoundException;
import org.example.testtaker.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ExceptionMessage> userNotFound(UserNotFoundException userNotFoundException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionMessage(userNotFoundException.getMessage()));
    }

    @ExceptionHandler(value = TestNotFoundException.class)
    public ResponseEntity<ExceptionMessage> testNotFound(TestNotFoundException testNotFoundException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionMessage(testNotFoundException.getMessage()));
    }
}
