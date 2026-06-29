package com.example.myflix.application.service;

import com.example.myflix.application.usecase.MovieUseCase;
import com.example.myflix.model.Movie;
import com.example.myflix.out.MovieRepositoryOutputPort;
import domain.exception.MovieException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService implements MovieUseCase {

    private final MovieRepositoryOutputPort movieRepositoryOutputPort;

    public MovieService(MovieRepositoryOutputPort movieRepositoryOutputPort) {
        this.movieRepositoryOutputPort = movieRepositoryOutputPort;
    }

    @Override
    public Movie create(Movie movie) {

        if (movie.getTitle() == null || movie.getTitle().isEmpty()) {
            throw MovieException.isMandatory("Título (title)");
        }

        if (movie.getGenres() == null || movie.getGenres().isEmpty()) {
            throw MovieException.isMandatory("Gênero (genres)");
        }

        if (movie.getReleaseYear() != null && movie.getReleaseYear() < 1888) {
            throw MovieException.invalidReleaseYear(movie.getReleaseYear());
        }

        if (movieRepositoryOutputPort.existsByTitleIgnoreCase(movie.getTitle())) {
            throw MovieException.alreadyExists(movie.getTitle());
        }
        return movieRepositoryOutputPort.save(movie);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepositoryOutputPort.findAll();
    }

    @Override
    public List<Movie> searchMovies(String title) {
        return movieRepositoryOutputPort.searchMovies(title);
    }

    @Override
    public Movie findById(String id) {
        return movieRepositoryOutputPort.findById(id).orElse(null);
    }

    @Override
    public void delete(String id) {
        if (!movieRepositoryOutputPort.existsById(id)) {
            throw MovieException.notFound(id);
        }
        movieRepositoryOutputPort.deleteById(id);
    }

    @Override
    public Movie update(String id, Movie movie) {
        Movie existingMovie = movieRepositoryOutputPort.findById(id)
                .orElseThrow(() -> MovieException.notFound(id));

        if (movie.getTitle() != null && !movie.getTitle().isEmpty()) {
            boolean titleChanged = !movie.getTitle()
                    .equalsIgnoreCase(existingMovie.getTitle());

            if (titleChanged &&
                    movieRepositoryOutputPort.existsByTitleIgnoreCase(movie.getTitle())) {
                throw MovieException.alreadyExists(movie.getTitle());
            }
            existingMovie.setTitle(movie.getTitle());
        }
        if (movie.getDescription() != null && !movie.getDescription().isEmpty()) {
            existingMovie.setDescription(movie.getDescription());
        }
        if (movie.getGenres() != null && !movie.getGenres().isEmpty()) {
            existingMovie.setGenres(movie.getGenres());
        }
        if (movie.getReleaseYear() != null) {
            existingMovie.setReleaseYear(movie.getReleaseYear());
        }

        return movieRepositoryOutputPort.save(existingMovie);
    }

}
