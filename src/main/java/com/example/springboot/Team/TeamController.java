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
    public ResponseEntity<Team> getTeamByName(@PathVariable String teamName){
        try{
            Team team = teamService.getTeamByName(teamName);
            return ResponseEntity.status(HttpStatus.OK).body(team);
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(path = "{teamName}")
    public ResponseEntity<String> createNewTeam(@PathVariable String teamName, @RequestBody Team team){
        try{
            teamService.createNewTeam(teamName,team);
            return ResponseEntity.ok().body("Team was created successfully");
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
