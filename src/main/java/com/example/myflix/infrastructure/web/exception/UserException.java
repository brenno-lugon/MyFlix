package com.example.myflix.infrastructure.web.exception;

public class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
    }

    public static UserException alreadyExists(String name) {
        return new UserException("Usuário '" + name + "' já está cadastrado.");
    }

    public static UserException notFound(String id) {
        return new UserException("Usuário com id '" + id + "' não encontrado.");
    }
}
