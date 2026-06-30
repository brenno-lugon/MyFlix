package com.example.myflix.infrastructure.mongo.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "movies")
public class MovieDocument {

    @Id
    private String id;
    private String title;
    private String description;
    private String genres;
    private Integer releaseYear;
    private LocalDateTime createdAt;
}