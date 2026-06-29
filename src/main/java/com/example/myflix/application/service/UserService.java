package com.example.myflix.application.service;

import com.example.myflix.application.usecase.UserUseCase;
import com.example.myflix.model.User;
import com.example.myflix.out.UserRepositoryOutputPort;
import domain.exception.UserException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserUseCase {

    private final UserRepositoryOutputPort userRepositoryOutputPort;

    public UserService(UserRepositoryOutputPort userRepositoryOutputPort) {
        this.userRepositoryOutputPort = userRepositoryOutputPort;
    }

    @Override
    public User create(User user) {
        if (userRepositoryOutputPort.existsByNameIgnoreCase(user.getName())) {
            throw UserException.alreadyExists(user.getName());
        }
        return userRepositoryOutputPort.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepositoryOutputPort.findAll();
    }

}
