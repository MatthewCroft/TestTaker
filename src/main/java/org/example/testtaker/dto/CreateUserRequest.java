package org.example.testtaker.dto;

public class CreateUserRequest {
    String name;

    public CreateUserRequest() {}

    public CreateUserRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
