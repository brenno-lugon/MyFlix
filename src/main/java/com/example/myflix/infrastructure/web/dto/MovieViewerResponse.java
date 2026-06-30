package com.example.myflix.infrastructure.web.dto;

import java.time.LocalDateTime;

public record MovieViewerResponse(String movieName, LocalDateTime watchedAt) {
}
