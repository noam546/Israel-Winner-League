package com.example.springboot.player;

import com.example.springboot.Statistics.Statistics;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Player {
    @Id
    private Long id;
    private String name;
    private PlayerPosition position;
    private double height;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id") // Use the same ID for Player and PlayerStats
    @JsonManagedReference
    private Statistics playerStatistics;
    private LocalDate dob;
    @Transient
    private int age;

    public Player(){

    }

    public Statistics getPlayerStatistics() {
        return playerStatistics;
    }

    public void setPlayerStatistics(Statistics playerStatistics) {
        this.playerStatistics = playerStatistics;
    }

    public int getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Player(Long id, String name, PlayerPosition position, double height, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.height = height;
        this.dob = dob;
    }

    public Player(String name, PlayerPosition position, double height, LocalDate dob) {
        this.name = name;
        this.position = position;
        this.height = height;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", height=" + height +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
