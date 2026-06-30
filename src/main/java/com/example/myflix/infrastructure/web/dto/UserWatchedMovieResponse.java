package com.example.myflix.infrastructure.web.dto;

import java.time.LocalDateTime;

public record UserWatchedMovieResponse(String userName, LocalDateTime watchedAt) {
}
