package com.example.myflix.adapter.in.web.dto;

import java.time.LocalDateTime;

public record UserWatchedMovieResponse(String userName, LocalDateTime watchedAt) {
}
