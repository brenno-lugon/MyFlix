package com.example.myflix.infrastructure.web.controller;

import com.example.myflix.domain.model.Movie;
import com.example.myflix.domain.port.in.MovieUseCase;
import com.example.myflix.infrastructure.web.dto.MovieRequest;
import com.example.myflix.infrastructure.web.dto.MovieResponse;
import com.example.myflix.infrastructure.web.mapper.MovieWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieUseCase movieUseCase;

    public MovieController(MovieUseCase movieUseCase) {
        this.movieUseCase = movieUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieResponse create(@RequestBody MovieRequest request) {
        Movie movie = MovieWebMapper.toDomain(request);
        Movie createdMovie = movieUseCase.create(movie);
        return MovieWebMapper.toResponse(createdMovie);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MovieResponse> findAll() {
        List<Movie> movieList = movieUseCase.findAll();
        return movieList.stream().map(MovieWebMapper::toResponse).toList();
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieResponse> searchByContainingTitle(@RequestParam String title) {
        List<Movie> movieList = movieUseCase.searchMovies(title);
        return movieList.stream().map(MovieWebMapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieResponse findById(@PathVariable String id) {
        Movie movie = movieUseCase.findById(id);
        return MovieWebMapper.toResponse(movie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        movieUseCase.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieResponse update(@PathVariable String id, @RequestBody MovieRequest request) {
        Movie movie = MovieWebMapper.toDomain(request);
        Movie updatedMovie = movieUseCase.update(id, movie);
        return MovieWebMapper.toResponse(updatedMovie);
    }
}
