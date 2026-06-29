package com.example.myflix.adapter.out.persistence.mongo.repository;

import com.example.myflix.adapter.out.persistence.mongo.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataUserRepository extends MongoRepository<UserDocument, String> {
    boolean existsByNameIgnoreCase(String name);
}
