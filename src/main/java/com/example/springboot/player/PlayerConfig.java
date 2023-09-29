package com.example.springboot.player;

import com.example.springboot.Statistics.Statistics;
import com.example.springboot.Statistics.StatisticsRepository;
import com.example.springboot.Team.Team;
import com.example.springboot.Team.TeamKey;
import com.example.springboot.Team.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class PlayerConfig {

    @Bean
    CommandLineRunner commandLineRunner(PlayerRepository playerRepository, StatisticsRepository statisticsRepository, TeamRepository teamRepository){
        return args -> {
            Player TK = new Player(1L,"Tarence Anthony Kinsey",PlayerPosition.SF,2.00, LocalDate.of(1984, Month.MARCH,21));
            Player alenOmic = new Player(2L,"alen omic",PlayerPosition.SF,2.00, LocalDate.of(1984, Month.MARCH,21));
            playerRepository.saveAll(List.of(TK,alenOmic));

            Statistics stat1 = new Statistics(1L);
            Statistics stat2 = new Statistics(2L);
            statisticsRepository.saveAll(List.of(stat1,stat2));


            Team team1 = new Team(new TeamKey("Hapoel Jerusalem","Winner League"));
            Team team2 = new Team(new TeamKey("Maccabi Tel Aviv","Winner League"));
            teamRepository.saveAll(List.of(team1,team2));
        };
    }
}
