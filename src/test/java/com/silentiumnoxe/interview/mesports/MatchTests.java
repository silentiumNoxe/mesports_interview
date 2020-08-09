package com.silentiumnoxe.interview.mesports;

import com.silentiumnoxe.interview.mesports.factory.MatchFactory;
import com.silentiumnoxe.interview.mesports.model.Match;
import com.silentiumnoxe.interview.mesports.model.User;
import com.silentiumnoxe.interview.mesports.service.UserService;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MesportsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Log
public class MatchTests extends TestSupport{

    @Autowired
    private MatchFactory matchFactory;

    @Autowired
    private UserService userService;

    private User user_1;
    private User user_2;
    private final User userNotInTournament = new User("userAAA");

    @BeforeEach
    void initUsers(){
        user_1 = getUser(userService, "user_1");
        user_2 = getUser(userService, "user_2");
    }

    @Test
    @DisplayName("test set winner of match")
    void setWinnerOfMatchTest(){
        Match match = matchFactory.create(user_1, user_2, 1);
        match.setWinner(user_1);
        assertEquals(user_1, match.getWinner());
        assertNotNull(match.getEndedDate());
    }

    @Test
    @DisplayName("test set winner of match which not in match")
    void setWinnerWhichNotInMatch(){
        Match match = matchFactory.create(user_1, user_2, 1);
        Throwable thrown =
                assertThrows(IllegalArgumentException.class, ()-> match.setWinner(userNotInTournament));

        assertNotNull(thrown);
    }
}
