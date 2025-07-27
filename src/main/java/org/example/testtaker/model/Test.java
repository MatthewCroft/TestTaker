package org.example.testtaker.model;

public class Test {
    Integer testId;
    String name;
    String createdBy;

    public Test(Integer testId, String name, String createdBy) {
        this.testId = testId;
        this.name = name;
        this.createdBy = createdBy;
    }
     public Test() {}

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
