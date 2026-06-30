package com.example.myflix.infrastructure.mongo.adapter;

import com.example.myflix.domain.model.Movie;
import com.example.myflix.domain.port.out.MovieRepository;
import com.example.myflix.infrastructure.mongo.document.MovieDocument;
import com.example.myflix.infrastructure.mongo.mapper.MoviePersistenceMapper;
import com.example.myflix.infrastructure.mongo.repository.SpringDataMovieRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MovieMongoAdapter implements MovieRepository {

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
        return springDataMovieRepository.findAll()
                .stream()
                .map(MoviePersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<Movie> searchMovies(String title) {
        return springDataMovieRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(MoviePersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(String id) {
        springDataMovieRepository.deleteById(id);
    }

    @Override
    public List<Movie> findAllById(List<String> ids) {
        List<MovieDocument> movieDocuments = springDataMovieRepository.findAllById(ids);
        return movieDocuments.stream()
                .map(MoviePersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsById(String id) {
        return springDataMovieRepository.existsById(id);
    }


}
