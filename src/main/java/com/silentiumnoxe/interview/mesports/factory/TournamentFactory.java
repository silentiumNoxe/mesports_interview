package com.silentiumnoxe.interview.mesports.factory;

import com.silentiumnoxe.interview.mesports.model.Tournament;
import com.silentiumnoxe.interview.mesports.model.User;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class TournamentFactory {

    public Tournament create(List<User> users){
        return new Tournament(users);
    }
}
