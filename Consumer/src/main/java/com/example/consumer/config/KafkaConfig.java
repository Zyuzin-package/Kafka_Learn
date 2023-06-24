package com.example.consumer.config;

import com.example.consumer.UserService;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan("com.example.consumer")
public class KafkaConfig {
    private final String kafkaServer="localhost:9092";

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> producerConfigProperties = new HashMap<>();
        producerConfigProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        producerConfigProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerConfigProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(producerConfigProperties);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<String,String>(producerFactory());
    }

    @Bean
    public UserService userService (){
        return new UserService();
    }

}
