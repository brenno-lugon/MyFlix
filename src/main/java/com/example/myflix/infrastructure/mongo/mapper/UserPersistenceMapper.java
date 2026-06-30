package com.example.myflix.infrastructure.mongo.mapper;

import com.example.myflix.domain.model.User;
import com.example.myflix.infrastructure.mongo.document.UserDocument;

public class UserPersistenceMapper {
    public static UserDocument toDocument(User user) {
        UserDocument document = new UserDocument();
        document.setId(user.getId());
        document.setName(user.getName());
        document.setCreatedAt(user.getCreatedAt());
        return document;
    }

    public static User toDomain(UserDocument document) {
        return new User(
                document.getId(),
                document.getName(),
                document.getCreatedAt()
        );
    }
}
