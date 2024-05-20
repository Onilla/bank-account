package com.testproject.userservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration

public class KafkaTopicConfig {
    @Value("${NEW_CARD_TOPIC}")
    private String newCardTopic;

    @Bean
    public NewTopic cardTopic() {
        return TopicBuilder.name(newCardTopic)
                .build();
    }
}
