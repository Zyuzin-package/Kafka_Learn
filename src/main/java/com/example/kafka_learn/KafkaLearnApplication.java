package com.example.kafka_learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
@SpringBootApplication
public class KafkaLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaLearnApplication.class, args);
    }


    @KafkaListener(topics="msg")
    public void msgListener(String msg, String msgId){
        System.out.println(msg);
    }
}
