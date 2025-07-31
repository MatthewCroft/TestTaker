package org.example.testtaker.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "test")
public class TestEntity {
    @Id
    @Column(name = "test_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer testId;

    String name;

    @Column(name = "created_by")
    String createdBy;

    public TestEntity(String name, String createdBy) {
        this.name = name;
        this.createdBy = createdBy;
    }

    public TestEntity(){}

    public Integer getTestId() {
        return testId;
    }

    public String getName() {
        return name;
    }

    public String getCreatedBy() {
        return createdBy;
    }
}
