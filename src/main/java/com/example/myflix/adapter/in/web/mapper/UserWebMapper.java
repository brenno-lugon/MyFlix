package com.example.myflix.adapter.in.web.mapper;

import com.example.myflix.adapter.in.web.dto.UserRequest;
import com.example.myflix.adapter.in.web.dto.UserResponse;
import com.example.myflix.model.User;

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
