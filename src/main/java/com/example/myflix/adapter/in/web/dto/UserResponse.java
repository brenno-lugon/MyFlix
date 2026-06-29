package com.example.myflix.adapter.in.web.dto;

import java.time.LocalDateTime;

public record UserResponse(String id,
                           String name,
                           LocalDateTime createdAt) {
}
