package com.example.myflix.infrastructure.kafka;

import com.example.myflix.domain.event.MovieWatchedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaMovieWatchedEventPublisher implements MovieWatchedEventPublisher {

    private static final String TOPIC = "movie-watched";

    private final KafkaTemplate<String, MovieWatchedEvent> kafkaTemplate;

    public KafkaMovieWatchedEventPublisher(
            KafkaTemplate<String, MovieWatchedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(MovieWatchedEvent event) {

        kafkaTemplate.send(TOPIC, event.userId(), event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        System.err.println("Erro ao publicar no Kafka: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                });
    }
}
