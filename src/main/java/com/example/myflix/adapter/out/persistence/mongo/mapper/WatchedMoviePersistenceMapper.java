package com.example.myflix.adapter.out.persistence.mongo.mapper;

import com.example.myflix.adapter.out.persistence.mongo.document.WatchedMovieDocument;
import com.example.myflix.model.WatchedMovie;

public class WatchedMoviePersistenceMapper {
    public static WatchedMovieDocument toDocument(WatchedMovie watchedMovie) {
        WatchedMovieDocument document = new WatchedMovieDocument();
        document.setId(watchedMovie.getId());
        document.setUserId(watchedMovie.getUserId());
        document.setMovieId(watchedMovie.getMovieId());
        document.setWatchedAt(watchedMovie.getWatchedAt());
        return document;
    }

    public static WatchedMovie toDomain(WatchedMovieDocument document) {
        return new WatchedMovie(
                document.getId(),
                document.getUserId(),
                document.getMovieId(),
                document.getWatchedAt()
        );
    }
}
