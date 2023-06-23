package com.example.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class ProducerApplication {
    @Autowired
    private KafkaTemplate<String, String> template;

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);

    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("topic1")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Scheduled(fixedDelay = 2500)
    public void runner() {
        template.send("topic1", "56");
        template.send("topic2", "78");
    }

}
