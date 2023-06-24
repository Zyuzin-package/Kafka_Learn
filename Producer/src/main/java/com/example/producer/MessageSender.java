package com.example.producer;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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

    @Scheduled(fixedDelay = 5000)
    public void runner() {
        User user = new User(String.valueOf(new Random().nextInt(78)), new Random().nextInt(56));
        JSONObject obj = new JSONObject();
        obj.put("name", user.getName());
        obj.put("age", user.getAge());

        String serializedUser = JSONValue.toJSONString(obj);
        System.out.println(serializedUser);
        template.send("topic1", serializedUser);
    }

}
