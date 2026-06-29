package com.example.myflix.adapter.in.web;

import com.example.myflix.adapter.in.web.dto.UserRequest;
import com.example.myflix.adapter.in.web.dto.UserResponse;
import com.example.myflix.adapter.in.web.mapper.UserWebMapper;
import com.example.myflix.application.usecase.UserUseCase;
import com.example.myflix.model.User;
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
