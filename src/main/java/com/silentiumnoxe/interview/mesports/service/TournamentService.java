package com.silentiumnoxe.interview.mesports.service;

import com.silentiumnoxe.interview.mesports.model.Tournament;
import com.silentiumnoxe.interview.mesports.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository repository;

    public Tournament save(Tournament tournament){
        return repository.save(tournament);
    }

    public Optional<Tournament> findById(Long id){
        return repository.findById(id);
    }

    public void delete(Tournament tournament){
        repository.delete(tournament);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
