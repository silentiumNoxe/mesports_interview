package com.silentiumnoxe.interview.mesports;

import com.silentiumnoxe.interview.mesports.factory.TournamentFactory;
import com.silentiumnoxe.interview.mesports.model.Match;
import com.silentiumnoxe.interview.mesports.model.Tournament;
import com.silentiumnoxe.interview.mesports.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MesportsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TournamentTests {

    @Autowired
    private TournamentFactory tournamentFactory;

    private final UserServiceTest userService = new UserServiceTest();


    private final User user_1 = new User("user_1");
    private final User user_2 = new User("user_2");

    private final List<User> twoUsers = List.of(user_1, user_2);
    private final User userNotInTournament = new User("userAAA");

    @Test
    @DisplayName("test creating match")
    void creatingMatchTest(){
        Tournament tournament = tournamentFactory.create(twoUsers);
        Match match = tournament.createMatch(user_1, user_2, 1);
        assertNotNull(match);
    }

    @Test
    @DisplayName("test creating match with user which not not in tournament")
    void createMatchWithNotInTournamentUser(){
        Tournament tournament = tournamentFactory.create(twoUsers);
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            tournament.createMatch(user_1, userNotInTournament, 1);
        });
        assertNotNull(thrown);
    }

    @Test
    @DisplayName("test removing user from tournament when tournament started")
    void removeUserWhenTournamentStartedTest(){
        User user = new User("test_remove_user");
        Tournament tournament = tournamentFactory.create(new ArrayList<>());
        tournament.addUser(user);
        tournament.start();
        tournament.removeUser(user);
        assertTrue(tournament.containsUser(user));
    }
}
