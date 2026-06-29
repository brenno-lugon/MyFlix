package com.example.myflix.adapter.out.persistence.mongo.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "watched_movie")
public class WatchedMovieDocument {

    @Id
    private String id;
    private String userId;
    private String movieId;
    private LocalDateTime watchedAt;
}