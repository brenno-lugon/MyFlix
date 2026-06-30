package com.example.myflix.infrastructure.kafka;

import com.example.myflix.domain.event.MovieWatchedEvent;

public interface MovieWatchedEventPublisher {
    void publish(MovieWatchedEvent event);
}
