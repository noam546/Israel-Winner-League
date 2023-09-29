package com.example.springboot.Team;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Team {
    @EmbeddedId
    private TeamKey key;

    private LocalDate foundationDate;

    private String country;

    private String homeCourt;

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
}

