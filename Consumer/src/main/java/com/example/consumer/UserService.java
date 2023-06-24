package com.example.consumer;

import java.util.Random;

public class UserService {
    public User updateUser(User user) {
        user.setAge(0);
        user.setName("KORKA  "+ new Random().nextInt(78));
        return user;
    }
}
