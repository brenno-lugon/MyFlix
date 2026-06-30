package com.example.myflix.infrastructure.mongo.adapter;

import com.example.myflix.domain.model.WatchedMovie;
import com.example.myflix.domain.port.out.WatchedMovieRepository;
import com.example.myflix.infrastructure.mongo.document.WatchedMovieDocument;
import com.example.myflix.infrastructure.mongo.mapper.WatchedMoviePersistenceMapper;
import com.example.myflix.infrastructure.mongo.repository.SpringDataWatchedMovieRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WatchedMovieMongoAdapter implements WatchedMovieRepository {

    private final SpringDataWatchedMovieRepository springDataWatchedMovieRepository;

    public WatchedMovieMongoAdapter(SpringDataWatchedMovieRepository springDataWatchedMovieRepository) {
        this.springDataWatchedMovieRepository = springDataWatchedMovieRepository;
    }

    @Override
    public WatchedMovie save(WatchedMovie watchedMovie) {
        WatchedMovieDocument document = WatchedMoviePersistenceMapper.toDocument(watchedMovie);
        WatchedMovieDocument savedDocument = springDataWatchedMovieRepository.save(document);
        return WatchedMoviePersistenceMapper.toDomain(savedDocument);
    }

    @Override
    public List<WatchedMovie> findByUserId(String userId) {
        return springDataWatchedMovieRepository.findByUserId(userId).stream()
                .map(WatchedMoviePersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<WatchedMovie> findByMovieId(String movieId) {
        return springDataWatchedMovieRepository.findByMovieId(movieId).stream()
                .map(WatchedMoviePersistenceMapper::toDomain)
                .toList();
    }

}
