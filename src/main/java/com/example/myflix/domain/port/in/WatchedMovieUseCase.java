package com.example.myflix.domain.port.in;

import com.example.myflix.domain.model.MovieViewedByUser;
import com.example.myflix.domain.model.UserWatchedMovie;
import com.example.myflix.domain.model.WatchedMovie;

import java.util.List;

public interface WatchedMovieUseCase {
    WatchedMovie create(WatchedMovie watchedMovie);

    List<MovieViewedByUser> findMoviesWatchedByUser(String userId);

    List<UserWatchedMovie> findUsersWhoWatchMovie(String movieId);
}
