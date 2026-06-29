package com.example.myflix.adapter.in.web.dto;

import java.time.LocalDateTime;

public record MovieResponse(String id,
                            String title,
                            String description,
                            String genres,
                            Integer releaseYear,
                            LocalDateTime createdAt) {
}
