package com.example.myflix.application.service;

import com.example.myflix.application.usecase.WatchedMovieUseCase;
import com.example.myflix.model.*;
import com.example.myflix.out.MovieRepositoryOutputPort;
import com.example.myflix.out.UserRepositoryOutputPort;
import com.example.myflix.out.WatchedMovieRepositoryOutputPort;
import domain.exception.WatchedMovieException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WatchedMovieService implements WatchedMovieUseCase {

    private final WatchedMovieRepositoryOutputPort watchedMovieRepositoryOutputPort;
    private final MovieRepositoryOutputPort movieRepositoryOutputPort;
    private final UserRepositoryOutputPort userRepositoryOutputPort;

    public WatchedMovieService(WatchedMovieRepositoryOutputPort watchedMovieRepositoryOutputPort, MovieRepositoryOutputPort movieRepositoryOutputPort, UserRepositoryOutputPort userRepositoryOutputPort) {
        this.watchedMovieRepositoryOutputPort = watchedMovieRepositoryOutputPort;
        this.movieRepositoryOutputPort = movieRepositoryOutputPort;
        this.userRepositoryOutputPort = userRepositoryOutputPort;
    }

    @Override
    public WatchedMovie create(WatchedMovie watchedMovie) {
        if (watchedMovie.getUserId() == null || watchedMovie.getUserId().isEmpty()) {
            throw WatchedMovieException.isMandatory("Id do Usuário");
        }
        if (watchedMovie.getMovieId() == null || watchedMovie.getMovieId().isEmpty()) {
            throw WatchedMovieException.isMandatory("Id do Filme");
        }

        return watchedMovieRepositoryOutputPort.save(watchedMovie);
    }

    @Override
    public List<MovieViewedByUser> findMoviesWatchedByUser(String userId) {

        List<WatchedMovie> watchedList =
                watchedMovieRepositoryOutputPort.findByUserId(userId);

        List<String> movieIds = watchedList.stream()
                .map(WatchedMovie::getMovieId)
                .toList();

        List<Movie> movies = movieRepositoryOutputPort.findAllById(movieIds);

        Map<String, Movie> movieMap = movies.stream()
                .collect(Collectors.toMap(Movie::getId, movie -> movie));

        return watchedList.stream()
                .map(watched -> {
                    Movie movie = movieMap.get(watched.getMovieId());

                    return new MovieViewedByUser(
                            movie.getTitle(),
                            watched.getWatchedAt()
                    );
                })
                .toList();
    }

    @Override
    public List<UserWatchedMovie> findUsersWhoWatchMovie(String movieId) {

        List<WatchedMovie> watchedList =
                watchedMovieRepositoryOutputPort.findByMovieId(movieId);

        List<String> userIds = watchedList.stream()
                .map(WatchedMovie::getUserId)
                .toList();

        List<User> users = userRepositoryOutputPort.findAllById(userIds);

        Map<String, User> userMap = users.stream()
                .collect(Collectors.toMap(User::getId, user -> user));

        return watchedList.stream()
                .map(watched -> {
                    User user = userMap.get(watched.getUserId());

                    return new UserWatchedMovie(
                            user.getName(),
                            watched.getWatchedAt()
                    );
                })
                .toList();
    }

}
