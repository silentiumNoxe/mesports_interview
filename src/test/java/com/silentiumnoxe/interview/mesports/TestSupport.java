package com.silentiumnoxe.interview.mesports;

import com.silentiumnoxe.interview.mesports.model.User;
import com.silentiumnoxe.interview.mesports.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class TestSupport {

    public User getUser(UserService userService, String name){
        return userService.findByName(name).orElseGet(() -> userService.save(new User(name)));
    }

    public List<User> getTestUsers(int length){
        List<User> users = new ArrayList<>();

        for(int i = 0; i < length; i++){
            users.add(new User("test_"+1));
        }

        return users;
    }
}
