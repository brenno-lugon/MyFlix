package com.example.myflix.adapter.out.persistence.mongo;

import com.example.myflix.adapter.out.persistence.mongo.document.WatchedMovieDocument;
import com.example.myflix.adapter.out.persistence.mongo.mapper.WatchedMoviePersistenceMapper;
import com.example.myflix.adapter.out.persistence.mongo.repository.SpringDataWatchedMovieRepository;
import com.example.myflix.model.WatchedMovie;
import com.example.myflix.out.WatchedMovieRepositoryOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WatchedMovieMongoAdapter implements WatchedMovieRepositoryOutputPort {

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
