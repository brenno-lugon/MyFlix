package com.example.myflix.adapter.out.persistence.mongo;

import com.example.myflix.adapter.out.persistence.mongo.document.MovieDocument;
import com.example.myflix.adapter.out.persistence.mongo.mapper.MoviePersistenceMapper;
import com.example.myflix.adapter.out.persistence.mongo.repository.SpringDataMovieRepository;
import com.example.myflix.model.Movie;
import com.example.myflix.out.MovieRepositoryOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MovieMongoAdapter implements MovieRepositoryOutputPort {

    private final SpringDataMovieRepository springDataMovieRepository;

    public MovieMongoAdapter(SpringDataMovieRepository springDataMovieRepository) {
        this.springDataMovieRepository = springDataMovieRepository;
    }

    @Override
    public Movie save(Movie movie) {
        MovieDocument document = MoviePersistenceMapper.toDocument(movie);
        MovieDocument savedDocument = springDataMovieRepository.save(document);
        return MoviePersistenceMapper.toDomain(savedDocument);
    }

    @Override
    public boolean existsByTitleIgnoreCase(String title) {
        return springDataMovieRepository.existsByTitleIgnoreCase(title);
    }

    @Override
    public Optional<Movie> findById(String id) {
        return springDataMovieRepository
                .findById(id)
                .map(MoviePersistenceMapper::toDomain);
    }

    @Override
    public List<Movie> findAll() {
        return List.of();
    }
}
