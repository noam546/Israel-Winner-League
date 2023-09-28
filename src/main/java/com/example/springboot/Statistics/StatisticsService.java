package com.example.springboot.Statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void addNewRow(Statistics statistics) {
        if(statisticsRepository.existsById(statistics.getId())){
            throw new IllegalStateException("player stats already exists");
        }
        statisticsRepository.save(statistics);
    }

    public void deleteRow(Long playerId) {
        if(!statisticsRepository.existsById(playerId)){
            throw new IllegalStateException("player stats does not exist");
        }
        statisticsRepository.deleteById(playerId);
    }

    @Transactional
    public void updateRow(Long id, Statistics statistics) {
        Statistics existingstats = statisticsRepository.findById(id).orElseThrow(
                ()-> new IllegalStateException("player does not exist")
        );
        updateRowValues(existingstats, statistics);
    }

    private void updateRowValues(Statistics existingStats, Statistics updatedStats){
        if (!Double.isNaN(updatedStats.getPPG())) {
            existingStats.setPPG(updatedStats.getPPG());
        }

        if (!Double.isNaN(updatedStats.getAPG())) {
            existingStats.setAPG(updatedStats.getAPG());
        }

        if (!Double.isNaN(updatedStats.getSPG())) {
            existingStats.setSPG(updatedStats.getSPG());
        }

        if (!Double.isNaN(updatedStats.getBPG())) {
            existingStats.setBPG(updatedStats.getBPG());
        }

        if (!Double.isNaN(updatedStats.getRPG())) {
            existingStats.setRPG(updatedStats.getRPG());
        }

        if (!Double.isNaN(updatedStats.getFTpercent())) {
            existingStats.setFTpercent(updatedStats.getFTpercent());
        }

        if (!Double.isNaN(updatedStats.getFGpercent())) {
            existingStats.setFGpercent(updatedStats.getFGpercent());
        }

        if (!Double.isNaN(updatedStats.getThreesPercent())) {
            existingStats.setThreesPercent(updatedStats.getThreesPercent());
        }

        if (!Double.isNaN(updatedStats.getMPG())) {
            existingStats.setMPG(updatedStats.getMPG());
        }

        if (updatedStats.getGS() != -1) {
            existingStats.setGS(updatedStats.getGS());
        }

        if (updatedStats.getGP() != -1) {
            existingStats.setGP(updatedStats.getGP());
        }
    }
}
