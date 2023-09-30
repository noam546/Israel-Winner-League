package com.example.springboot.Team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
