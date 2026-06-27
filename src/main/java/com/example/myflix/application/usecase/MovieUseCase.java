package com.example.myflix.application.usecase;

import com.example.myflix.model.Movie;

import java.util.List;

public interface MovieUseCase {
    Movie create(Movie movie);

    List<Movie> findAll();

    List<Movie> searchByTitle(String title);

    Movie findById(String id);
}
