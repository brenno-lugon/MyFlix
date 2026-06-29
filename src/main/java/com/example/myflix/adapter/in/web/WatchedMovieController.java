package com.example.myflix.adapter.in.web;

import com.example.myflix.adapter.in.web.dto.MovieViewerResponse;
import com.example.myflix.adapter.in.web.dto.UserWatchedMovieResponse;
import com.example.myflix.adapter.in.web.dto.WatchedMovieRequest;
import com.example.myflix.adapter.in.web.dto.WatchedMovieResponse;
import com.example.myflix.adapter.in.web.mapper.WatchedMovieWebMapper;
import com.example.myflix.application.usecase.WatchedMovieUseCase;
import com.example.myflix.model.MovieViewedByUser;
import com.example.myflix.model.UserWatchedMovie;
import com.example.myflix.model.WatchedMovie;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/watchedMovie")
public class WatchedMovieController {

    private final WatchedMovieUseCase watchedMovieUseCase;

    public WatchedMovieController(WatchedMovieUseCase watchedMovieUseCase) {
        this.watchedMovieUseCase = watchedMovieUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WatchedMovieResponse create(@RequestBody WatchedMovieRequest request) {
        WatchedMovie watchedMovie = WatchedMovieWebMapper.toDomain(request);
        WatchedMovie createdWatchedMovie = watchedMovieUseCase.create(watchedMovie);
        return WatchedMovieWebMapper.toResponse(createdWatchedMovie);
    }

    @GetMapping("/moviesByUserId")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieViewerResponse> moviesByUserId(@RequestParam String userId) {
        List<MovieViewedByUser> movieList = watchedMovieUseCase.findMoviesWatchedByUser(userId);
        return movieList.stream().map(WatchedMovieWebMapper::toResponse).toList();
    }

    @GetMapping("/usersByMovieId")
    @ResponseStatus(HttpStatus.OK)
    public List<UserWatchedMovieResponse> usersByMovieId(@RequestParam String movieId) {
        List<UserWatchedMovie> userList = watchedMovieUseCase.findUsersWhoWatchMovie(movieId);
        return userList.stream().map(WatchedMovieWebMapper::toResponse).toList();
    }


}
