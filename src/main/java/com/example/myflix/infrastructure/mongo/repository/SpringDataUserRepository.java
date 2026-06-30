package com.example.myflix.infrastructure.mongo.repository;

import com.example.myflix.infrastructure.mongo.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataUserRepository extends MongoRepository<UserDocument, String> {
    boolean existsByNameIgnoreCase(String name);
}
