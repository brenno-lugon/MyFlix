package com.example.myflix.adapter.out.persistence.mongo.mapper;

import com.example.myflix.adapter.out.persistence.mongo.document.UserDocument;
import com.example.myflix.model.User;

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
