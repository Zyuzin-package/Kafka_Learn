package com.example.consumer.listeners;

import com.example.consumer.MessageSender;
import com.example.consumer.User;
import com.example.consumer.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserListener {

    UserService userService;
    MessageSender messageSender;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @KafkaListener(id = "myId1", topics = "topic1")
    public void listenTopic1(String in) {
        if (null != in && !in.isEmpty()) {
            try {
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(in);
                System.out.println("=> " + jsonObject);

                String name = (String) jsonObject.get("name");
                int age = Math.toIntExact((Long) jsonObject.get("age"));

                User user = new User(name,age);
                User updatedUser = userService.updateUser(user);
                System.out.println("Updated user: "+ updatedUser);
                messageSender.sendUpdatedUserToProducerBack(updatedUser);
            } catch (ParseException exception) {
                System.out.println(exception);
            }
        }
    }

    @KafkaListener(id = "myId2", topics = "topic2")
    public void listenTopic2(String in) {
        System.out.println("topic2: " + in);
    }
}
