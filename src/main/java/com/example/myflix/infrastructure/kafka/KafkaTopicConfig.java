package com.example.myflix.infrastructure.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic movieWatchedTopic() {
        return TopicBuilder.name("movie-watched")
                .partitions(1)
                .replicas(1)
                .build();
    }
}