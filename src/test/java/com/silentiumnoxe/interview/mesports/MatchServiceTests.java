package com.silentiumnoxe.interview.mesports;

import com.silentiumnoxe.interview.mesports.factory.MatchFactory;
import com.silentiumnoxe.interview.mesports.model.Match;
import com.silentiumnoxe.interview.mesports.model.User;
import com.silentiumnoxe.interview.mesports.service.MatchService;
import com.silentiumnoxe.interview.mesports.service.UserService;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = MesportsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Log
public class MatchServiceTests extends TestSupport{

    @Autowired
    private MatchService matchService;

    @Autowired
    private MatchFactory matchFactory;

    @Autowired
    private UserService userService;

    private User user_1;
    private User user_2;

    @BeforeEach
    void initUsers(){
        user_1 = getUser(userService, "user_1");
        user_2 = getUser(userService, "user_2");
    }

    @Test
    @DisplayName("test saving match")
    void saveTest(){
        Match match = matchService.save(
                matchFactory.create(user_1, user_2, 1));
        assertNotNull(match);
    }

    @Test
    @DisplayName("test removing match")
    void removeTest(){
        Match match = getMatch();
        Long id = match.getId();
        if(id == null) match = matchService.save(match);

        matchService.delete(match);
        assertTrue(matchService.findById(id).isEmpty());
    }

    @Test
    @DisplayName("test setting creation date")
    void creationDateTest(){
        Match match = getMatch();
        assertNotNull(match.getCreatedDate());
    }

    private Match getMatch(){
        return matchService.findById(0L)
                .orElseGet(() -> matchService.save(matchFactory.create(user_1, user_2, 1)));
    }
}
