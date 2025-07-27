package org.example.testtaker.repository;

import org.example.testtaker.entities.QuestionEntity;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<QuestionEntity, Integer> {}
