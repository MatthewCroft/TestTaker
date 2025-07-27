package org.example.testtaker.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "exam_user")
public class ExamUser {
    @Id
    @Column(name = "exam_user_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer examUserId;

    String name;

    public ExamUser() {}

    public ExamUser(String name) {
        this.name = name;
    }

    public int getExamUserId() {
        return examUserId;
    }

    public String getName() {
        return name;
    }
}
