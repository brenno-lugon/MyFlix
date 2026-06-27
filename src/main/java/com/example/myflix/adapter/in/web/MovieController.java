package com.example.myflix.adapter.in.web;

import com.example.myflix.adapter.in.web.dto.MovieRequest;
import com.example.myflix.adapter.in.web.dto.MovieResponse;
import com.example.myflix.adapter.in.web.mapper.MovieWebMapper;
import com.example.myflix.application.usecase.MovieUseCase;
import com.example.myflix.model.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

}
