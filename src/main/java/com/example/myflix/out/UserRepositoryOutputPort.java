package com.example.myflix.out;

import com.example.myflix.model.User;

import java.util.List;

public interface UserRepositoryOutputPort {
    User save(User user);

    boolean existsByNameIgnoreCase(String name);

    List<User> findAll();

    List<User> findAllById(List<String> ids);
}
