package com.example.springboot.Team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    @Autowired
    public TeamService(TeamRepository teamRepository){this.teamRepository = teamRepository;}
}
