package com.example.myflix.adapter.in.web.mapper;

import com.example.myflix.adapter.in.web.dto.MovieRequest;
import com.example.myflix.adapter.in.web.dto.MovieResponse;
import com.example.myflix.model.Movie;

public class MovieWebMapper {
    public static Movie toDomain(MovieRequest request) {
        return Movie.create(
                request.title(),
                request.description(),
                request.genres(),
                request.releaseYear()
        );
    }

    public static MovieResponse toResponse(Movie movie) {
        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getGenres(),
                movie.getReleaseYear(),
                movie.getCreatedAt()
        );
    }
}
