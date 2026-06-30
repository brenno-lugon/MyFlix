package com.example.myflix.infrastructure.web.mapper;

import com.example.myflix.domain.model.Movie;
import com.example.myflix.infrastructure.web.dto.MovieRequest;
import com.example.myflix.infrastructure.web.dto.MovieResponse;

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
