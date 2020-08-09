package com.silentiumnoxe.interview.mesports;

import com.silentiumnoxe.interview.mesports.model.User;
import com.silentiumnoxe.interview.mesports.service.UserService;
import lombok.extern.java.Log;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = MesportsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Log
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("test saving match")
    void saveTest(){
        User user = userService.save(new User("user1"));
        assertNotNull(user);
    }

    @Test
    @DisplayName("test removing match")
    void removeTest(){
        User user = userService.findById(0L).orElseGet(() -> userService.save(new User("user1")));
        Long id = user.getId();
        if(id == null) user = userService.save(user);

        userService.delete(user);
        assertTrue(userService.findById(id).isEmpty());
    }
}
