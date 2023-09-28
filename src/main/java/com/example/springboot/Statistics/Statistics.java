package com.example.springboot.Statistics;
import com.example.springboot.player.Player;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table
public class Statistics {
    @Id
    private Long id;
    private double PPG;
    private double APG;
    private double SPG;
    private double BPG;
    private double RPG;
    //free throw percent
    private double FTpercent;
    private double FGpercent;
    private double threesPercent;
    private double MPG;
    @OneToOne(mappedBy = "playerStatistics")
    @JsonBackReference
    private Player player;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Statistics(){}

    public Statistics(double PPG, double APG, double SPG) {
        this.PPG = PPG;
        this.APG = APG;
        this.SPG = SPG;
    }

    public double getPPG() {
        return PPG;
    }

    public void setPPG(double PPG) {
        this.PPG = PPG;
    }

    public double getAPG() {
        return APG;
    }

    public void setAPG(double APG) {
        this.APG = APG;
    }

    public double getSPG() {
        return SPG;
    }

    public void setSPG(double SPG) {
        this.SPG = SPG;
    }

    public double getBPG() {
        return BPG;
    }

    public void setBPG(double BPG) {
        this.BPG = BPG;
    }

    public double getRPG() {
        return RPG;
    }

    public void setRPG(double RPG) {
        this.RPG = RPG;
    }

    public double getFTpercent() {
        return FTpercent;
    }

    public void setFTpercent(double FTpercent) {
        this.FTpercent = FTpercent;
    }

    public double getFGpercent() {
        return FGpercent;
    }

    public void setFGpercent(double FGpercent) {
        this.FGpercent = FGpercent;
    }

    public double getThreesPercent() {
        return threesPercent;
    }

    public void setThreesPercent(double threesPercent) {
        this.threesPercent = threesPercent;
    }

    public double getMPG() {
        return MPG;
    }

    public void setMPG(double MPG) {
        this.MPG = MPG;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", PPG=" + PPG +
                ", APG=" + APG +
                ", SPG=" + SPG +
                '}';
    }
}
