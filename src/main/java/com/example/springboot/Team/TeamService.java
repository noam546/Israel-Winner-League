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

    public List<Team> getTeamByName(String teamName) {
        List<Team> teams = teamRepository.findByKeyName(teamName);
        if(teams.isEmpty()){
            throw new IllegalStateException("team not found");
        }
        return teams;
    }

    public Team getTeamByKey(TeamKey key){
        return teamRepository.findById(key).orElseThrow(
                ()-> new IllegalStateException("Team does not exist")
        );
    }

    public boolean createNewTeam(Team team) {
        if(isTeamExist(team)){
            return false;
        }
        teamRepository.save(team);
        return true;
    }

    public boolean isTeamExist(Team team){
        return teamRepository.existsById(team.getKey());
    }

}
