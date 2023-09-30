package com.example.springboot.player;
import com.example.springboot.Team.Team;
import com.example.springboot.Team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.InputMismatchException;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final StatisticsRepository statisticsRepository;
    private final TeamService teamService;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, TeamService teamService, StatisticsRepository statisticsRepository) {
        this.playerRepository = playerRepository;
        this.teamService = teamService;
        this.statisticsRepository = statisticsRepository;
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

        player.setId(playerId);
        playerRepository.save(player);
    }

    private boolean validateCurrentTeamAndSetIt(Player player, Team team){
        try{
            Team existingTeam = teamService.getTeamByName(team.getName());
            player.setCurrentTeam(existingTeam);
            return true;
        }catch (IllegalStateException e){
            return false;
        }
    }

    private boolean validatePlayerStatsAndSetIt(Player player, PlayerStats stats){
        try{
            PlayerStats existingStats = statisticsRepository.findById(stats.getId()).
                    orElseThrow(
                    ()-> new IllegalArgumentException("Player was not found")
            );
            player.setPlayerStats(existingStats);
            return true;
        }catch (Exception e){
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
        if(updatedPlayer.getPlayerStats() != null){
            if(!validatePlayerStatsAndSetIt(existingPlayer,updatedPlayer.getPlayerStats())){
                throw new IllegalArgumentException("Stats do not exist");
            }
        }
    }

    ////////// Stats

    public PlayerStats getStatisticsById(Long id) {
        return statisticsRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Player was not found")
        );
    }

    public void addNewRow(Long playerId, PlayerStats playerStats) {
        Player player = playerRepository.findById(playerId).orElseThrow(
                ()-> new IllegalArgumentException("Player was not found")
        );
        System.out.println("passed player is found");

        if(statisticsRepository.existsById(playerId)){
            throw new InputMismatchException("Player stats already exists");
        }
        System.out.println("passed stats not found");

        playerStats.setPlayer(player);
        System.out.println("passed set player");

        playerStats.setId(playerId);
        System.out.println("passed ");
        statisticsRepository.save(playerStats);
    }

    public void deleteRow(Long playerId) {
        if(!statisticsRepository.existsById(playerId)){
            throw new IllegalArgumentException("player stats does not exist");
        }
        statisticsRepository.deleteById(playerId);
    }

    @Transactional
    public void updateRow(Long id, PlayerStats updatedStats) {
        PlayerStats existingStats = statisticsRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Player's stats does not exist")
        );
        updateRowValues(existingStats, updatedStats);
    }

    private void updateRowValues(PlayerStats existingStats, PlayerStats updatedStats){
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
