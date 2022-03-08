package com.deo.microservices.conversionmultipleservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("currencyInfo1").partitions(3).replicas(3).build();
    }

    @Bean
    public NewTopic topic2() {
        return TopicBuilder.name("currencyInfo2").partitions(3).replicas(3).build();
    }

    @Bean
    public NewTopic topic3() {
        return TopicBuilder.name("currencyInfo3").partitions(3).replicas(3).build();
    }
}
