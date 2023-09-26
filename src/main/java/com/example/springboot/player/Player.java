package com.example.springboot.player;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Player {
    @Id
    @SequenceGenerator(
            name = "player_sequence",
            sequenceName = "player_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "player_sequence"
    )
    private Long id;
    private String name;
    private PlayerPosition position;
    private double height;
//    private PlayerStats stats;
    private LocalDate dob;
    @Transient
    private int age;

    public Player(){

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

//    public Player(Long id, String name, PlayerPosition position, double height, PlayerStats stats, LocalDate dob) {
//        this.id = id;
//        this.name = name;
//        this.position = position;
//        this.height = height;
//        this.stats = stats;
//        this.dob = dob;
//    }

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

//    public PlayerStats getStats() {
//        return stats;
//    }
//
//    public void setStats(PlayerStats stats) {
//        this.stats = stats;
//    }

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
