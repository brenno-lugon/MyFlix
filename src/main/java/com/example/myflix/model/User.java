package com.example.myflix.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class User {
    private String id;
    private String name;
    private LocalDateTime createdAt;

    public User(String id, String name, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    public static User create(String name) {
        return new User(null, name, LocalDateTime.now());
    }
}


