package com.example.producer.listeners;

import com.example.producer.User;
import org.apache.kafka.clients.admin.NewTopic;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserListener {




    @KafkaListener(id = "myId1", topics = "topic1")
    public void listenTopic1(String in) {
        if (null != in && !in.isEmpty()) {
            try {
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(in);

                String name = (String) jsonObject.get("name");
                int age = Math.toIntExact((Long) jsonObject.get("age"));

                User user = new User(name,age);
                System.out.println("=> "+user);
            } catch (ParseException exception) {
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
