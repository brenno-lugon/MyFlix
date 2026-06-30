package com.example.myflix.infrastructure.mongo.repository;

import com.example.myflix.infrastructure.mongo.document.MovieDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpringDataMovieRepository extends MongoRepository<MovieDocument, String> {
    boolean existsByTitleIgnoreCase(String title);

    List<MovieDocument> findByTitleContainingIgnoreCase(String title);

}
