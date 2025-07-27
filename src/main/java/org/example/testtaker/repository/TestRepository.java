package org.example.testtaker.repository;

import org.example.testtaker.entities.TestEntity;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<TestEntity, Integer> {}
