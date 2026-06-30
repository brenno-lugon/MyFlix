package com.example.myflix.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MovieViewedByUser {
    private String movieName;
    private LocalDateTime watchedAt;

    public MovieViewedByUser(String movieName, LocalDateTime watchedAt) {
        this.movieName = movieName;
        this.watchedAt = watchedAt;
    }
}


