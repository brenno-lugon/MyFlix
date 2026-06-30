package com.example.myflix.domain.port.in;

import com.example.myflix.domain.model.Movie;

import java.util.List;

public interface MovieUseCase {
    Movie create(Movie movie);

    List<Movie> findAll();

    List<Movie> searchMovies(String title);

    Movie findById(String id);

    void delete(String id);

    Movie update(String id, Movie movie);
}
