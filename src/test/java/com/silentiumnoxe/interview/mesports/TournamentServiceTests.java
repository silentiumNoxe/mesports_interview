package com.silentiumnoxe.interview.mesports;

import com.silentiumnoxe.interview.mesports.factory.TournamentFactory;
import com.silentiumnoxe.interview.mesports.model.Match;
import com.silentiumnoxe.interview.mesports.model.Tournament;
import com.silentiumnoxe.interview.mesports.model.User;
import com.silentiumnoxe.interview.mesports.service.TournamentService;
import com.silentiumnoxe.interview.mesports.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MesportsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TournamentServiceTests extends TestSupport{

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private TournamentFactory tournamentFactory;

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("test saving tournament to database")
    void saveTest(){
        Tournament tournament = tournamentFactory.create(new ArrayList<>());
        assertNotNull(tournament);

        tournament = tournamentService.save(tournament);
        assertNotNull(tournament);
        assertNotNull(tournament.getId());
        assertNotNull(tournament.getCreatedDate());

        Tournament found = tournamentService.findById(tournament.getId()).orElseThrow();
        assertEquals(tournament.getId(), found.getId());
    }

    @Test
    @DisplayName("test setting creation date")
    void creationDateTest(){
        Tournament tournament = getTournament();
        assertNotNull(tournament.getCreatedDate());
    }

    @Test
    @DisplayName("test adding user")
    void addUserTest(){
        Tournament tournament = getTournament();
        int oldUserSize = tournament.getUsers().size();
        tournament.addUser(new User("test_addUser"));

        tournament = tournamentService.save(tournament);
        assertNotNull(tournament);
        assertNotEquals(oldUserSize, tournament.getUsers().size());
    }

    @Test
    @DisplayName("test adding match")
    void addMatchTest(){
        User left = getUser(userService, "left");
        User right = getUser(userService, "right");
        Match match = new Match(left, right, 1);
        Tournament tournament = getTournament();
        tournament.setMatches(new ArrayList<>());

        tournament.addUser(left);
        tournament.addUser(right);
        tournament.addMatch(match);

        tournament = tournamentService.save(tournament);
        Match found = tournament.getMatches().get(0);
        found.setId(null);
        found.setCreatedDate(null);
        assertEquals(match, found);
    }

    @Test
    @DisplayName("test setting winner")
    void setWinnerTest(){
        User winner = new User("test_winner");
        Tournament tournament = getTournament();
        tournament.addUser(winner);
        tournament.setWinner(winner);

        tournament = tournamentService.save(tournament);
        assertEquals(winner.getName(), tournament.getWinner().getName());
    }

    @Test
    @DisplayName("test changing state pause/start")
    void changeStateTest(){
        Tournament tournament = getTournament();
        boolean paused = tournament.isPaused();
        tournament.setPaused(!paused);

        tournament = tournamentService.save(tournament);
        assertNotEquals(paused, tournament.isPaused());
    }

    @Test
    @DisplayName("test removing tournament from database")
    void removeTest(){
        Tournament tournament = getTournament();

        tournamentService.delete(tournament);
        assertTrue(tournamentService.findById(0L).isEmpty());
    }

    private Tournament getTournament(){
        return tournamentService.findById(0L)
                .orElseGet(() -> tournamentService.save(tournamentFactory.create(new ArrayList<>())));
    }
}
