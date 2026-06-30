package com.example.myflix.infrastructure.web.dto;

import java.time.LocalDateTime;

public record UserResponse(String id,
                           String name,
                           LocalDateTime createdAt) {
}
