package com.silentiumnoxe.interview.mesports;

import com.silentiumnoxe.interview.mesports.factory.MatchFactory;
import com.silentiumnoxe.interview.mesports.factory.TournamentFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguratorApplication {

    @Bean
    public MatchFactory matchFactory(){
        return new MatchFactory();
    }

    @Bean
    public TournamentFactory tournamentFactory(){
        return new TournamentFactory();
    }
}
