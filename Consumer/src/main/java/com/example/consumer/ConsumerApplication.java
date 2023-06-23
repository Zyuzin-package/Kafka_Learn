package com.example.consumer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("topic1")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topic2() {
        return TopicBuilder.name("topic2")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @KafkaListener(id = "myId1", topics = "topic1")
    public void listenTopic1(String in) {
        System.out.println("topic1: " + in);
    }
    @KafkaListener(id = "myId2", topics = "topic2")
    public void listenTopic2(String in) {
        System.out.println("topic2: " + in);
    }
}