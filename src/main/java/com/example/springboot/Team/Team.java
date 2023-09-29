package com.example.springboot.Team;

import com.example.springboot.player.Player;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Team {
    @EmbeddedId
    private TeamKey key;

    private LocalDate foundationDate;

    private String country;

    private String homeCourt;

    @OneToMany(mappedBy = "currentTeam", cascade = CascadeType.ALL)
    private Set<Player> players = new HashSet<>();

    public Team(){};

    public Team(TeamKey key){this.key = key;}

    public TeamKey getKey() {
        return key;
    }

    public void setKey(TeamKey key) {
        this.key = key;
    }

    public LocalDate getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(LocalDate foundationDate) {
        this.foundationDate = foundationDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHomeCourt() {
        return homeCourt;
    }

    public void setHomeCourt(String homeCourt) {
        this.homeCourt = homeCourt;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}

