package com.example.springboot.Team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    @Autowired
    public TeamService(TeamRepository teamRepository){this.teamRepository = teamRepository;}

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamByName(String teamName) {
        return teamRepository.findById(teamName).orElseThrow(
                ()-> new IllegalArgumentException("Team was not found")
        );
    }

    public void createNewTeam(String teamName, Team team) {
        if(teamRepository.existsById(teamName)){
            throw new IllegalArgumentException("Team already exists");
        }
        team.setName(teamName);
        teamRepository.save(team);
    }


    public void deleteTeam(String teamName) {
        if(!teamRepository.existsById(teamName)){
            throw new IllegalArgumentException("Team does not exists");
        }
        teamRepository.deleteById(teamName);
    }

    @Transactional
    public void updateTeam(String teamName, Team updatedTeam) {
        Team existingTeam = teamRepository.findById(teamName).orElseThrow(
                ()-> new IllegalArgumentException("Team was not found")
        );
        updateTeamDetails(existingTeam, updatedTeam);
    }

    private void updateTeamDetails(Team existingTeam, Team updatedTeam){
        if(updatedTeam.getFoundationDate() != null){
            existingTeam.setFoundationDate(updatedTeam.getFoundationDate());
        }
        System.out.println("in update");
        if(updatedTeam.getCountry() != null){
            System.out.println(updatedTeam.getCountry());
            existingTeam.setCountry(updatedTeam.getCountry());
        }

        if(updatedTeam.getHomeCourt() != null){
            existingTeam.setHomeCourt(updatedTeam.getHomeCourt());
        }
    }
}
