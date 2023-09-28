package com.example.springboot.Team;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Team {
    @Id
    private String name;
}
