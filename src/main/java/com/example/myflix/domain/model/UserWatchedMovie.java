package com.example.myflix.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserWatchedMovie {
    private String userName;
    private LocalDateTime watchedAt;

    public UserWatchedMovie(String userName, LocalDateTime watchedAt) {
        this.userName = userName;
        this.watchedAt = watchedAt;
    }
}


