package com.example.myflix.infrastructure.mongo.repository;

import com.example.myflix.infrastructure.mongo.document.WatchedMovieDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpringDataWatchedMovieRepository extends MongoRepository<WatchedMovieDocument, String> {

    List<WatchedMovieDocument> findByUserId(String userId);

    List<WatchedMovieDocument> findByMovieId(String movieId);

}
