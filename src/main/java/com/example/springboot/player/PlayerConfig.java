package com.example.springboot.player;

import com.example.springboot.Statistics.Statistics;
import com.example.springboot.Statistics.StatisticsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class PlayerConfig {

    @Bean
    CommandLineRunner commandLineRunner(PlayerRepository repository, StatisticsRepository statisticsRepository){
        return args -> {
            Player TK = new Player(1L,"Tarence Anthony Kinsey",PlayerPosition.SF,2.00, LocalDate.of(1984, Month.MARCH,21));
            Player alenOmic = new Player(2L,"alen omic",PlayerPosition.SF,2.00, LocalDate.of(1984, Month.MARCH,21));
            repository.saveAll(List.of(TK,alenOmic));

            Statistics stat1 = new Statistics(1L);
            Statistics stat2 = new Statistics(2L);
            statisticsRepository.saveAll(List.of(stat1,stat2));
        };
    }
}
