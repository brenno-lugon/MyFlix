package com.example.myflix.domain.port.out;

import com.example.myflix.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);

    boolean existsByNameIgnoreCase(String name);

    List<User> findAll();

    List<User> findAllById(List<String> ids);

    Optional<User> findById(String id);
}
