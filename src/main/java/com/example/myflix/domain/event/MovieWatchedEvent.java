package com.example.myflix.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record MovieWatchedEvent(
        UUID eventId,
        LocalDateTime occurredAt,
        String userId,
        String userName,
        String movieId,
        String movieTitle,
        LocalDateTime watchedAt
) {
}
