package com.silentiumnoxe.interview.mesports.service;

import com.silentiumnoxe.interview.mesports.model.Match;
import com.silentiumnoxe.interview.mesports.model.User;
import com.silentiumnoxe.interview.mesports.repository.MatchRepository;
import com.silentiumnoxe.interview.mesports.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User save(User user){
        return repository.save(user);
    }

    public void saveAll(List<User> users){
        repository.saveAll(users);
    }

    public Optional<User> findById(Long id){
        return repository.findById(id);
    }

    public Optional<User> findByName(String name){
        return repository.findByName(name);
    }

    public void delete(User user){
        repository.delete(user);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
