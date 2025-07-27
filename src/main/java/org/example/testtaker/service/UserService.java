package org.example.testtaker.service;

import org.example.testtaker.dto.CreateUserRequest;
import org.example.testtaker.entities.ExamUser;
import org.example.testtaker.model.User;
import org.example.testtaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
       this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequest user) {
        ExamUser examUser = new ExamUser(user.getName());
        ExamUser createdUser = userRepository.save(examUser);
        return new User(createdUser.getExamUserId(), examUser.getName());
    }

    public User getUser(Integer userId) {
        ExamUser examUser = userRepository.findById(userId).orElse(null);
        if (examUser == null) return null;
        return new User(examUser.getExamUserId(), examUser.getName());
    }
}
