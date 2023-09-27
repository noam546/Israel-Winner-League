package com.example.springboot.Statistics;

import com.example.springboot.player.Player;
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

    @GetMapping(path = "{playerid}")
    public Statistics getStatistics(@PathVariable Long playerid){
        return statisticsService.getStatisticsById(playerid);
    }

    @GetMapping
    public List<Statistics> getAllStatistics(){
        return statisticsService.getAllStatistics();
    }

    @PostMapping(path = "{playerid}")
    public void addNewRow(@PathVariable Long playerid,
                          @RequestBody Statistics statistics){
        try {
            System.out.println(statistics);
            Player player = playerService.getPlayerById(playerid);

            statistics.setPlayer(player);
            statistics.setId(playerid);
            statisticsService.addNewRow(statistics);

        } catch (IllegalStateException ex) {
            throw new IllegalStateException("id does not exist");
        }
    }

}
