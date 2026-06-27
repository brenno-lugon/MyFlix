package com.example.myflix.adapter.in.web.dto;

public record MovieRequest(String title,
                           String description,
                           String genre,
                           Integer releaseYear) {

}
