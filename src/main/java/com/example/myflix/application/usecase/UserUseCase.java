package com.example.myflix.application.usecase;

import com.example.myflix.model.User;

import java.util.List;

public interface UserUseCase {
    User create(User user);

    List<User> findAll();
}
