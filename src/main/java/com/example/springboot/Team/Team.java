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


}

