package com.example.myflix.infrastructure.mongo.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "user")
public class UserDocument {

    @Id
    private String id;
    private String name;
    private LocalDateTime createdAt;
}