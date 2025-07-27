package org.example.testtaker.dto;

public class CreateTestRequest {
    String name;
    Integer createdBy;

    public CreateTestRequest(){}
    public CreateTestRequest(String name, Integer createdBy) {
        this.name = name;
        this.createdBy = createdBy;
    }

    public String getName() {
        return name;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }
}
