package com.example.myflix.infrastructure.web.mapper;

import com.example.myflix.domain.model.User;
import com.example.myflix.infrastructure.web.dto.UserRequest;
import com.example.myflix.infrastructure.web.dto.UserResponse;

public class UserWebMapper {
    public static User toDomain(UserRequest request) {
        return User.create(
                request.name()
        );
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getCreatedAt()
        );
    }
}
