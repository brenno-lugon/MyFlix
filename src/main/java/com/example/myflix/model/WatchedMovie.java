package com.example.myflix.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WatchedMovie {
    private String id;
    private String userId;
    private String movieId;
    private LocalDateTime watchedAt;

    public WatchedMovie(String id, String userId, String movieId, LocalDateTime watchedAt) {
        this.id = id;
        this.userId = userId;
        this.movieId = movieId;
        this.watchedAt = watchedAt;
    }

    public static WatchedMovie create(String userId, String movieId) {
        return new WatchedMovie(null, userId, movieId, LocalDateTime.now());
    }
}


