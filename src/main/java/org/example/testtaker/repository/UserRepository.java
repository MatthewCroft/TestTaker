package org.example.testtaker.repository;

import org.example.testtaker.entities.ExamUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface UserRepository extends CrudRepository<ExamUser, String> {}
