package com.example.myflix.application.service;

import com.example.myflix.domain.event.MovieWatchedEvent;
import com.example.myflix.domain.model.*;
import com.example.myflix.domain.port.in.WatchedMovieUseCase;
import com.example.myflix.domain.port.out.MovieRepository;
import com.example.myflix.domain.port.out.UserRepository;
import com.example.myflix.domain.port.out.WatchedMovieRepository;
import com.example.myflix.infrastructure.kafka.MovieWatchedEventPublisher;
import com.example.myflix.infrastructure.web.exception.MovieException;
import com.example.myflix.infrastructure.web.exception.UserException;
import com.example.myflix.infrastructure.web.exception.WatchedMovieException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WatchedMovieService implements WatchedMovieUseCase {

    private final WatchedMovieRepository watchedMovieRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final MovieWatchedEventPublisher publisher;

    public WatchedMovieService(WatchedMovieRepository watchedMovieRepository, MovieRepository movieRepository, UserRepository userRepository, MovieWatchedEventPublisher publisher) {
        this.watchedMovieRepository = watchedMovieRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.publisher = publisher;
    }

    @Override
    public WatchedMovie create(WatchedMovie watchedMovie) {
        if (watchedMovie.getUserId() == null || watchedMovie.getUserId().isEmpty()) {
            throw WatchedMovieException.isMandatory("Id do Usuário");
        }
        if (watchedMovie.getMovieId() == null || watchedMovie.getMovieId().isEmpty()) {
            throw WatchedMovieException.isMandatory("Id do Filme");
        }

        WatchedMovie saved = watchedMovieRepository.save(watchedMovie);

        Movie movie = movieRepository.findById(watchedMovie.getMovieId())
                .orElseThrow(() -> MovieException.notFound("Filme"));
        User user = userRepository.findById(watchedMovie.getUserId())
                .orElseThrow(() -> UserException.notFound("Filme"));

        MovieWatchedEvent event = new MovieWatchedEvent(
                UUID.randomUUID(),
                LocalDateTime.now(),
                saved.getUserId(),
                user.getName(),
                saved.getMovieId(),
                movie.getTitle(),
                saved.getWatchedAt()
        );

        publisher.publish(event);

        return saved;
    }

    @Override
    public List<MovieViewedByUser> findMoviesWatchedByUser(String userId) {

        List<WatchedMovie> watchedList =
                watchedMovieRepository.findByUserId(userId);

        List<String> movieIds = watchedList.stream()
                .map(WatchedMovie::getMovieId)
                .toList();

        List<Movie> movies = movieRepository.findAllById(movieIds);

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
                watchedMovieRepository.findByMovieId(movieId);

        List<String> userIds = watchedList.stream()
                .map(WatchedMovie::getUserId)
                .toList();

        List<User> users = userRepository.findAllById(userIds);

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
