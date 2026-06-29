package com.example.myflix.adapter.out.persistence.mongo.mapper;

import com.example.myflix.adapter.out.persistence.mongo.document.MovieDocument;
import com.example.myflix.model.Movie;

public class MoviePersistenceMapper {
    public static MovieDocument toDocument(Movie movie) {
        MovieDocument document = new MovieDocument();

        document.setId(movie.getId());
        document.setTitle(movie.getTitle());
        document.setDescription(movie.getDescription());
        document.setGenres(movie.getGenres());
        document.setReleaseYear(movie.getReleaseYear());
        document.setCreatedAt(movie.getCreatedAt());

        return document;
    }

    public static Movie toDomain(MovieDocument document) {
        return new Movie(
                document.getId(),
                document.getTitle(),
                document.getDescription(),
                document.getGenres(),
                document.getReleaseYear(),
                document.getCreatedAt()
        );
    }
}
