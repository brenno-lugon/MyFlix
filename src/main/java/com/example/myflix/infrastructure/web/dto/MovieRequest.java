package com.example.myflix.infrastructure.web.dto;

public record MovieRequest(String title,
                           String description,
                           String genres,
                           Integer releaseYear) {
}
