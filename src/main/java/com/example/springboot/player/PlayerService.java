package com.example.springboot.player;
import com.example.springboot.Statistics.Statistics;
import com.example.springboot.Statistics.StatisticsService;
import com.example.springboot.Team.Team;
import com.example.springboot.Team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final StatisticsService statisticsService;
    private final TeamService teamService;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, StatisticsService statisticsService, TeamService teamService) {
        this.playerRepository = playerRepository;
        this.statisticsService = statisticsService;
        this.teamService = teamService;
    }

    public List<Player> getAllPlayers(){
        try{
            return playerRepository.findAll();
        }catch (Exception e){
            throw new IllegalStateException("Error occurred while fetching teams", e);
        }
    }

    public void createNewPlayer(Long playerId, Player player){
        if(playerRepository.existsById(playerId)){
            throw new IllegalArgumentException("player already exists");
        }

        if(player.getCurrentTeam() != null){
            if(!validateCurrentTeamAndSetIt(player,player.getCurrentTeam())){
                throw new InputMismatchException("Team does not exist");
            }
        }
        if(player.getPlayerStatistics() != null){
            if(!validatePlayerStatsAndSetIt(player,player.getPlayerStatistics())){
                throw new InputMismatchException("Stats do not exist");
            }
        }
        player.setId(playerId);
        playerRepository.save(player);
    }

    private boolean validateCurrentTeamAndSetIt(Player player, Team team){
        try{
            Team existingTeam = teamService.getTeamByKey(team.getKey());
            player.setCurrentTeam(existingTeam);
            return true;
        }catch (IllegalStateException e){
            return false;
        }
    }

    private boolean validatePlayerStatsAndSetIt(Player player, Statistics stats){
        try{
            Statistics existingStats = statisticsService.getStatisticsById(stats.getId());
            player.setPlayerStatistics(existingStats);
            return true;
        }catch (IllegalStateException e){
            return false;
        }
    }


    public void deletePlayer(Long playerId) {
        if(!playerRepository.existsById(playerId)){
            throw new IllegalArgumentException("player does not exist");
        }
        playerRepository.deleteById(playerId);
    }

    @Transactional
    public void updatePlayer(Long playerId,
                                  Player updatedPlayer) {
        Player existingPlayer = playerRepository.findById(playerId).
                orElseThrow(()-> new IllegalArgumentException("player does not exist"));
        updatePlayerStats(existingPlayer, updatedPlayer);
    }

    public Player getPlayerById(Long playerId) {
        return playerRepository.findById(playerId).
                orElseThrow(()-> new IllegalStateException("player does not exist"));
    }

    private void updatePlayerStats(Player existingPlayer, Player updatedPlayer){
        if(updatedPlayer.getName() != null){
            existingPlayer.setName(updatedPlayer.getName());
        }
        if(updatedPlayer.getPosition() != null){
            existingPlayer.setPosition(updatedPlayer.getPosition());
        }
        if(updatedPlayer.getHeight() != 0.0){
            existingPlayer.setHeight(updatedPlayer.getHeight());
        }
        if(updatedPlayer.getDob() != null){
            existingPlayer.setDob(updatedPlayer.getDob());
        }
        if(updatedPlayer.getNationality() != null){
            existingPlayer.setNationality(updatedPlayer.getNationality());
        }
        if(updatedPlayer.getCurrentTeam() != null){
            if(!validateCurrentTeamAndSetIt(existingPlayer,updatedPlayer.getCurrentTeam())){
                throw new IllegalArgumentException("Team does not exist");
            }
        }
        if(updatedPlayer.getPlayerStatistics() != null){
            if(!validatePlayerStatsAndSetIt(existingPlayer,updatedPlayer.getPlayerStatistics())){
                throw new IllegalArgumentException("Stats do not exist");
            }
        }
    }
}
