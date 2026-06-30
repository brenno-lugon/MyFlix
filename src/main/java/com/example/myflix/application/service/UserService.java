package com.example.myflix.application.service;

import com.example.myflix.domain.model.User;
import com.example.myflix.domain.port.in.UserUseCase;
import com.example.myflix.domain.port.out.UserRepository;
import com.example.myflix.infrastructure.web.exception.UserException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserUseCase {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        if (userRepository.existsByNameIgnoreCase(user.getName())) {
            throw UserException.alreadyExists(user.getName());
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
