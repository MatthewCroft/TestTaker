package org.example.testtaker.dto;

public class CreateTestRequest {
    String name;

    public CreateTestRequest(){}
    public CreateTestRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
