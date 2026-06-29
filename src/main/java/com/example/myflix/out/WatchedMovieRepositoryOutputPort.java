package com.example.myflix.out;

import com.example.myflix.model.WatchedMovie;

import java.util.List;

public interface WatchedMovieRepositoryOutputPort {
    WatchedMovie save(WatchedMovie watchedMovie);

    List<WatchedMovie> findByUserId(String userId);

    List<WatchedMovie> findByMovieId(String movieId);
}
