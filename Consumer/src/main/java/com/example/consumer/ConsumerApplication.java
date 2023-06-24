package com.example.consumer;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(ConsumerApplication.class, args);
    }


}