package com.example.myflix.domain.port.in;

import com.example.myflix.domain.model.User;

import java.util.List;

public interface UserUseCase {
    User create(User user);

    List<User> findAll();
}
