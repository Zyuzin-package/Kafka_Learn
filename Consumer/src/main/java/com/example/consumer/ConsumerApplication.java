package com.example.consumer;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Properties;


@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @KafkaListener(id = "myId1", topics = "topic1")
    public void listenTopic1(String in) {
        if (null != in && !in.isEmpty()) {
            try {
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(in);
                System.out.println(jsonObject);
            } catch ( ParseException  exception){
                System.out.println(in);
                System.out.println(exception);
            }
        }
    }

    @KafkaListener(id = "myId2", topics = "topic2")
    public void listenTopic2(String in) {
        System.out.println("topic2: " + in);
    }
}