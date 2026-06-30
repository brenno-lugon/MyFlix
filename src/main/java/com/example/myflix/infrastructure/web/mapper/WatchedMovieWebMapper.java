package com.example.myflix.infrastructure.web.mapper;

import com.example.myflix.domain.model.MovieViewedByUser;
import com.example.myflix.domain.model.UserWatchedMovie;
import com.example.myflix.domain.model.WatchedMovie;
import com.example.myflix.infrastructure.web.dto.MovieViewerResponse;
import com.example.myflix.infrastructure.web.dto.UserWatchedMovieResponse;
import com.example.myflix.infrastructure.web.dto.WatchedMovieRequest;
import com.example.myflix.infrastructure.web.dto.WatchedMovieResponse;

public class WatchedMovieWebMapper {
    public static WatchedMovie toDomain(WatchedMovieRequest request) {
        return WatchedMovie.create(
                request.userId(), request.movieId()
        );
    }

    public static WatchedMovieResponse toResponse(WatchedMovie watchedMovie) {
        return new WatchedMovieResponse(
                watchedMovie.getId(),
                watchedMovie.getUserId(),
                watchedMovie.getMovieId(),
                watchedMovie.getWatchedAt()
        );
    }

    public static MovieViewerResponse toResponse(MovieViewedByUser movieViewedByUser) {
        return new MovieViewerResponse(
                movieViewedByUser.getMovieName(),
                movieViewedByUser.getWatchedAt()
        );
    }

    public static UserWatchedMovieResponse toResponse(UserWatchedMovie userWatchedMovie) {
        return new UserWatchedMovieResponse(
                userWatchedMovie.getUserName(),
                userWatchedMovie.getWatchedAt()
        );
    }
}
