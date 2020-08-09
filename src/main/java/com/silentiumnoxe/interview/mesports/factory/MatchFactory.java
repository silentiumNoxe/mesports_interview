package com.silentiumnoxe.interview.mesports.factory;

import com.silentiumnoxe.interview.mesports.model.Match;
import com.silentiumnoxe.interview.mesports.model.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MatchFactory {

    public Match create(User leftUser, User rightUser, int tourNumber){
        return new Match(leftUser, rightUser, tourNumber);
    }
}
