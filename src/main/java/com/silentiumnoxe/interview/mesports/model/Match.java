package com.silentiumnoxe.interview.mesports.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Match {

    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    private Date createdDate;

    private Date endedDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User leftUser;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User rightUser;

    private int tourNumber;

    @ManyToOne
    private User winner;

    public Match(User leftUser, User rightUser, int tourNumber){
        this.leftUser = leftUser;
        this.rightUser = rightUser;
        this.tourNumber = tourNumber;
    }

    public void setWinner(User user){
        if(!user.equals(leftUser) && !user.equals(rightUser))
            throw new IllegalArgumentException(user.getName() + " not in this match");

        endedDate = new Date();
        winner = user;
    }
}
