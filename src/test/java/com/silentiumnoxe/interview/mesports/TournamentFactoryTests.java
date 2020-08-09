package com.silentiumnoxe.interview.mesports;

import com.silentiumnoxe.interview.mesports.factory.TournamentFactory;
import com.silentiumnoxe.interview.mesports.model.Tournament;
import com.silentiumnoxe.interview.mesports.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = MesportsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TournamentFactoryTests extends TestSupport{

    @Autowired
    private TournamentFactory tournamentFactory;

    @Test
    @DisplayName("test creating tournament (0 users)")
    void createTournamentWithoutUsersTest(){
        final List<User> users = new ArrayList<>();
        Tournament tournament = tournamentFactory.create(users);
        assertNotNull(tournament);
        assertEquals(0, tournament.getUsers().size(), "wrong users size");
    }

    @Test
    @DisplayName("test creating tournament (3 users)")
    void createTournamentWithOddAmountTest(){
        final List<User> users = getTestUsers(3);
        Tournament tournament = tournamentFactory.create(users);
        assertNotNull(tournament);
        assertEquals(3, tournament.getUsers().size(), "wrong users size");
    }

    @Test
    @DisplayName("test creating tournament (4 users)")
    void createTournamentWithEvenAmountTest(){
        final List<User> users = getTestUsers(4);
        Tournament tournament = tournamentFactory.create(users);
        assertNotNull(tournament);
        assertEquals(4, tournament.getUsers().size(), "wrong users size");
    }
}
