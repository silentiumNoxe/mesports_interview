package com.silentiumnoxe.interview.mesports.service;

import com.silentiumnoxe.interview.mesports.model.Match;
import com.silentiumnoxe.interview.mesports.model.Tournament;
import com.silentiumnoxe.interview.mesports.repository.MatchRepository;
import com.silentiumnoxe.interview.mesports.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private MatchRepository repository;

    public Match save(Match match){
        return repository.save(match);
    }

    public Optional<Match> findById(Long id){
        return repository.findById(id);
    }

    public void delete(Match match){
        repository.delete(match);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
