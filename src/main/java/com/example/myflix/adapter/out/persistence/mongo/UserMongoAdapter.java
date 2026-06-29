package com.example.myflix.adapter.out.persistence.mongo;

import com.example.myflix.adapter.out.persistence.mongo.document.UserDocument;
import com.example.myflix.adapter.out.persistence.mongo.mapper.UserPersistenceMapper;
import com.example.myflix.adapter.out.persistence.mongo.repository.SpringDataUserRepository;
import com.example.myflix.model.User;
import com.example.myflix.out.UserRepositoryOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMongoAdapter implements UserRepositoryOutputPort {

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

}
