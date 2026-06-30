package com.example.myflix.infrastructure.kafka;

import com.example.myflix.domain.event.MovieWatchedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MovieWatchedEventConsumer {

    @KafkaListener(
            topics = "movie-watched",
            groupId = "recommendation-service-test"
    )

    public void consume(MovieWatchedEvent event) {
        System.out.println("Evento recebido: Filme " + event.movieTitle() + " assistido por " + event.userName());
    }
}