package com.silentiumnoxe.interview.mesports.model;

import com.silentiumnoxe.interview.mesports.factory.MatchFactory;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
public class Tournament {

    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    private Date createdDate;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<User> users;

    @Getter(AccessLevel.NONE)
    private Boolean paused = true;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Match> matches;

    @ManyToOne
    private User winner;

    public Tournament(){
        this(new ArrayList<>());
    }

    public Tournament(List<User> users){
        this.users = users;
    }

    public void start(){
        paused = true;
    }

    public Match createMatch(User leftUser, User rightUser, int tour){
        if(!users.contains(leftUser))
            throw new IllegalArgumentException("User with name '"+leftUser.getName()+"' not in this tournament");
        if(!users.contains(rightUser))
            throw new IllegalArgumentException("User with name '"+rightUser.getName()+"' not in this tournament");
        return new MatchFactory().create(leftUser, rightUser, tour);
    }

    public boolean isPaused(){
        return paused;
    }

    public void addMatch(Match match){
        if(!users.contains(match.getLeftUser())){
            throw new IllegalArgumentException("User with name '"+match.getLeftUser().getName()+"' not in tournament");
        }
        if(!users.contains(match.getRightUser())){
            throw new IllegalArgumentException("User with name '"+match.getRightUser().getName()+"' not in tournament");
        }
        matches.add(match);
    }

    public void setUsers(List<User> users){
        if(paused) return;
        this.users = users;
    }

    public void addUser(User user){
        if(paused) users.add(user);
    }

    public boolean containsUser(User user){
        return users.contains(user);
    }

    public void removeUser(User user){
        if(paused || users == null) return;
        users.remove(user);
    }
}
