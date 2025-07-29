package org.example.testtaker.integration;

import org.example.testtaker.controller.QuestionController;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionIntegrationTest {

    @Autowired
    QuestionController questionController;

    @Autowired


    @BeforeAll
    public static void testSetup() {

    }

    @AfterAll
    public static void testTeardown() {

    }

    @Test
    public void testQuestion() {
    }
}
