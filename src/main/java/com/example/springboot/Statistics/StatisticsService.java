package com.example.springboot.Statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    @Autowired
    public StatisticsService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    public Statistics getStatisticsById(Long id) {
        Statistics statistics = statisticsRepository.findById(id).orElseThrow(
                ()-> new IllegalStateException("id is not exist")
        );
        return statistics;
    }

    public List<Statistics> getAllStatistics() {
        return statisticsRepository.findAll();
    }
}
