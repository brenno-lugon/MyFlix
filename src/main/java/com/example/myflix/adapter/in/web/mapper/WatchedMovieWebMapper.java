package com.example.myflix.adapter.in.web.mapper;

import com.example.myflix.adapter.in.web.dto.MovieViewerResponse;
import com.example.myflix.adapter.in.web.dto.UserWatchedMovieResponse;
import com.example.myflix.adapter.in.web.dto.WatchedMovieRequest;
import com.example.myflix.adapter.in.web.dto.WatchedMovieResponse;
import com.example.myflix.model.MovieViewedByUser;
import com.example.myflix.model.UserWatchedMovie;
import com.example.myflix.model.WatchedMovie;

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
