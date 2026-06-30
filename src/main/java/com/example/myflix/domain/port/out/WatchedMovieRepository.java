package com.example.myflix.domain.port.out;

import com.example.myflix.domain.model.WatchedMovie;

import java.util.List;

public interface WatchedMovieRepository {
    WatchedMovie save(WatchedMovie watchedMovie);

    List<WatchedMovie> findByUserId(String userId);

    List<WatchedMovie> findByMovieId(String movieId);
}
