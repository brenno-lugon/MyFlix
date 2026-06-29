package com.example.myflix.adapter.in.web.dto;

import java.time.LocalDateTime;

public record MovieViewerResponse(String movieName, LocalDateTime watchedAt) {
}
