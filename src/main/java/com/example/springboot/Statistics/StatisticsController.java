package com.example.springboot.Statistics;

import com.example.springboot.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "api/v1/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final PlayerService playerService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService, PlayerService playerService){
        this.statisticsService = statisticsService;
        this.playerService = playerService;
    }

    @GetMapping(path = "{Id}")
    public Statistics getStatistics(@PathVariable Long id){
        return statisticsService.getStatisticsById(id);
    }

    @GetMapping
    public List<Statistics> getAllStatistics(){
        return statisticsService.getAllStatistics();
    }

//    @PutMapping(path = "{Id}")
//    public void addNewRow(@PathVariable Long id,
//                          @RequestBody Statistics statistics){
//
//        // Check if the player with the provided ID exists
//        Player player = playerService.
//
//        if (player == null) {
//            return ResponseEntity.badRequest().body("Player with ID " + playerId + " does not exist.");
//        }
//
//        // If the player exists, create the PlayerStats entity and associate it with the player
//        playerStats.setPlayer(player);
//        playerStatsService.save(playerStats);
//    }

}
