package org.example.testtaker.service;

import org.example.testtaker.dto.CreateQuestionRequest;
import org.example.testtaker.entities.QuestionEntity;
import org.example.testtaker.exceptions.TestNotFoundException;
import org.example.testtaker.exceptions.UserNotFoundException;
import org.example.testtaker.exceptions.UserNotTestCreatorException;
import org.example.testtaker.model.Question;
import org.example.testtaker.model.User;
import org.example.testtaker.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    @Mock
    QuestionRepository questionRepository;

    @Mock
    UserService userService;

    @Mock
    TestService testService;

    @InjectMocks
    QuestionService questionService;

    @Test
    public void testQuestionServiceThrowsExceptionWhenTestNotFound() {
        Integer testId = 1;
        String userId = "Matt";
        when(testService.getTest(testId)).thenReturn(null);
        Exception e = assertThrows(TestNotFoundException.class, () -> questionService.createQuestion(new CreateQuestionRequest("question", ""), testId, userId));
        assertEquals("test with id 1 not found", e.getMessage());
    }

    @Test
    public void testQuestionServiceThrowExceptionWhenUserNotFound() {
        Integer testId = 1;
        String userId = "Matt";
        when(testService.getTest(testId)).thenReturn(new org.example.testtaker.model.Test());
        when(userService.getUser(userId)).thenReturn(null);
        Exception e = assertThrows(UserNotFoundException.class, () -> questionService.createQuestion(new CreateQuestionRequest("question", ""), testId, userId));
        assertEquals("user not found with id Matt", e.getMessage());
    }

    @Test
    public void testQuestionServiceThrowsExceptionIfUserTriesToCreateAQuestionOfATestTheyDoNotOwn() {
        Integer testId = 1;
        String userId = "Matt";
        String testOwnerId = "John";
        String testName = "first test";
        when(testService.getTest(testId)).thenReturn(new org.example.testtaker.model.Test(testId, testName, testOwnerId));
        when(userService.getUser(userId)).thenReturn(new User(userId));

        Exception e = assertThrows(UserNotTestCreatorException.class, () -> questionService.createQuestion(new CreateQuestionRequest("question", ""), testId, userId));
        assertEquals("user Matt is not the creator of the test, cannot create questions", e.getMessage());
    }

   @Test
   public void testQuestionServiceSavesQuestion() throws Exception {
       Integer testId = 1;
       String userId = "Matt";
       String testName = "first test";
       when(testService.getTest(testId)).thenReturn(new org.example.testtaker.model.Test(testId, testName, userId));
       when(userService.getUser(userId)).thenReturn(new User(userId));

       CreateQuestionRequest createQuestionRequest = new CreateQuestionRequest("question", "");
       when(questionRepository.save(any())).thenReturn(new QuestionEntity(testId, createQuestionRequest.getQuestion(), createQuestionRequest.getMedia()));

       Question question = questionService.createQuestion(createQuestionRequest, testId, userId);
       assertEquals(new Question(null, testId, createQuestionRequest.getQuestion(), createQuestionRequest.getMedia()), question);
   }
}
