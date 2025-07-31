package org.example.testtaker.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "exam_user")
public class ExamUser {
    @Id
    String name;

    public ExamUser() {}

    public ExamUser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
