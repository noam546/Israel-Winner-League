package com.example.springboot.Team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping(path = "api/v1/team")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService){this.teamService = teamService;}

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams(){
        try {
            List<Team> teams = teamService.getAllTeams();
            return ResponseEntity.ok(teams != null ? teams : Collections.emptyList());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @GetMapping(path = "{teamName}")
    public ResponseEntity<List<Team>> getTeamByName(@PathVariable String teamName){
        List<Team> teamList = teamService.getTeamByName(teamName);
        if(teamList == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(200).body(teamList);
    }

    @PostMapping
    public ResponseEntity<String> createNewTeam(@RequestBody Team team){
        if(!isValidRequest(team.getKey())){
            return ResponseEntity.badRequest().
                    body("Team name and league name are required");
        }
        if (teamService.createNewTeam(team)) {
            return ResponseEntity.status(201).
                    body("Team created successfully"); // 201 Created
        } else {
            return ResponseEntity.status(409).
                    body("Team already exists"); // 409 Conflict
        }
    }

    private boolean isValidRequest(TeamKey key){
        if(key == null ||
                key.getName() == null ||
                key.getLeagueName() == null){
            return false;
        }
        return true;
    }
}
