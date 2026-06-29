package com.example.myflix.out;

import com.example.myflix.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepositoryOutputPort {
    Movie save(Movie movie);

    boolean existsByTitleIgnoreCase(String title);

    Optional<Movie> findById(String id);

    List<Movie> findAll();

    List<Movie> searchMovies(String title);

    boolean existsById(String id);

    void deleteById(String id);

    List<Movie> findAllById(List<String> movieIds);
}
