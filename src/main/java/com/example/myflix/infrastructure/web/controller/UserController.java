package com.example.myflix.infrastructure.web.controller;

import com.example.myflix.domain.model.User;
import com.example.myflix.domain.port.in.UserUseCase;
import com.example.myflix.infrastructure.web.dto.UserRequest;
import com.example.myflix.infrastructure.web.dto.UserResponse;
import com.example.myflix.infrastructure.web.mapper.UserWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody UserRequest request) {
        User user = UserWebMapper.toDomain(request);
        User createdUser = userUseCase.create(user);
        return UserWebMapper.toResponse(createdUser);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> findAll() {
        List<User> userList = userUseCase.findAll();
        return userList.stream().map(UserWebMapper::toResponse).toList();
    }

}
