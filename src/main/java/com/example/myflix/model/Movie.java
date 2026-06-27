package com.example.myflix.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Movie {
    private String id;
    private String title;
    private String description;
    private String genre;
    private Integer releaseYear;
    private LocalDateTime createdAt;

    public Movie(String id, String title, String description, String genre, Integer releaseYear, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.createdAt = createdAt;
    }

    public static Movie create(String title, String description, String genre, Integer releaseYear) {
        return new Movie(null, title, description, genre, releaseYear, LocalDateTime.now());
    }
}


