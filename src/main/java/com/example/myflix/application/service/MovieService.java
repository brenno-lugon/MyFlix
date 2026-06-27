package com.example.myflix.application.service;

import com.example.myflix.application.usecase.MovieUseCase;
import com.example.myflix.model.Movie;
import org.springframework.stereotype.Service;
import com.example.myflix.out.MovieRepositoryOutputPort;

import java.util.List;

@Service
public class MovieService implements MovieUseCase {

    private final MovieRepositoryOutputPort movieRepositoryOutputPort;

    public MovieService(MovieRepositoryOutputPort movieRepositoryOutputPort) {
        this.movieRepositoryOutputPort = movieRepositoryOutputPort;
    }

    @Override
    public Movie create(Movie movie) {
        if (movieRepositoryOutputPort.existsByTitleIgnoreCase(movie.getTitle())) {
            throw new RuntimeException("Filme já está cadastrado!");
        }

        return movieRepositoryOutputPort.save(movie);
    }

    @Override
    public List<Movie> findAll() {
        return List.of();
    }

    @Override
    public List<Movie> searchByTitle(String title) {
        return List.of();
    }

    @Override
    public Movie findById(String id) {
        return null;
    }
}
