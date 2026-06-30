package com.example.myflix.infrastructure.mongo.adapter;

import com.example.myflix.domain.model.User;
import com.example.myflix.domain.port.out.UserRepository;
import com.example.myflix.infrastructure.mongo.document.UserDocument;
import com.example.myflix.infrastructure.mongo.mapper.UserPersistenceMapper;
import com.example.myflix.infrastructure.mongo.repository.SpringDataUserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserMongoAdapter implements UserRepository {

    private final SpringDataUserRepository springDataUserRepository;

    public UserMongoAdapter(SpringDataUserRepository springDataUserRepository) {
        this.springDataUserRepository = springDataUserRepository;
    }

    @Override
    public User save(User user) {
        UserDocument document = UserPersistenceMapper.toDocument(user);
        UserDocument savedDocument = springDataUserRepository.save(document);
        return UserPersistenceMapper.toDomain(savedDocument);
    }

    @Override
    public boolean existsByNameIgnoreCase(String name) {
        return springDataUserRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public List<User> findAll() {
        return springDataUserRepository.findAll()
                .stream()
                .map(UserPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<User> findAllById(List<String> ids) {
        List<UserDocument> userDocuments = springDataUserRepository.findAllById(ids);
        return userDocuments.stream()
                .map(UserPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<User> findById(String id) {
        return springDataUserRepository
                .findById(id)
                .map(UserPersistenceMapper::toDomain);
    }

}
