package com.example.myflix.application.service;

import com.example.myflix.domain.model.Movie;
import com.example.myflix.domain.port.in.MovieUseCase;
import com.example.myflix.domain.port.out.MovieRepository;
import com.example.myflix.infrastructure.web.exception.MovieException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService implements MovieUseCase {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
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

        if (movieRepository.existsByTitleIgnoreCase(movie.getTitle())) {
            throw MovieException.alreadyExists(movie.getTitle());
        }
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String title) {
        return movieRepository.searchMovies(title);
    }

    @Override
    public Movie findById(String id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(String id) {
        if (!movieRepository.existsById(id)) {
            throw MovieException.notFound(id);
        }
        movieRepository.deleteById(id);
    }

    @Override
    public Movie update(String id, Movie movie) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> MovieException.notFound(id));

        if (movie.getTitle() != null && !movie.getTitle().isEmpty()) {
            boolean titleChanged = !movie.getTitle()
                    .equalsIgnoreCase(existingMovie.getTitle());

            if (titleChanged &&
                    movieRepository.existsByTitleIgnoreCase(movie.getTitle())) {
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

        return movieRepository.save(existingMovie);
    }

}
