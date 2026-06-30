package com.example.myflix.infrastructure.web.dto;

import java.time.LocalDateTime;

public record WatchedMovieResponse(String id,
                                   String userId,
                                   String movieId,
                                   LocalDateTime watchedAt) {
}
