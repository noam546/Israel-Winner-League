package com.example.springboot.Team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping(path = "api/v1/team")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService){this.teamService = teamService;}

    @GetMapping
    public List<Team> getAllTeams(){
        return teamService.getAllTeams();
    }

    @GetMapping(path = "{teamName}")
    public List<Team> getTeamByName(@PathVariable String teamName){
        return teamService.getTeamByName(teamName);
    }
}
