package com.example.consumer;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MessageSender {

    private KafkaTemplate<String, String> template;

    @Autowired
    public void setTemplate(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    public void sendUpdatedUserToProducerBack(User user) {
        JSONObject obj = new JSONObject();
        obj.put("name", user.getName());
        obj.put("age", user.getAge());

        String serializedUser = JSONValue.toJSONString(obj);
        template.send("topic1", serializedUser);
    }
}
