package com.example.myflix.application.usecase;

import com.example.myflix.model.MovieViewedByUser;
import com.example.myflix.model.UserWatchedMovie;
import com.example.myflix.model.WatchedMovie;

import java.util.List;

public interface WatchedMovieUseCase {
    WatchedMovie create(WatchedMovie watchedMovie);

    List<MovieViewedByUser> findMoviesWatchedByUser(String userId);

    List<UserWatchedMovie> findUsersWhoWatchMovie(String movieId);
}
