package com.example.springboot.player;

public class PlayerStats {

    Double PPG;
    Double APG;
    Double SPG;

    public PlayerStats(Double PPG, Double APG, Double SPG) {
        this.PPG = PPG;
        this.APG = APG;
        this.SPG = SPG;
    }

    public Double getPPG() {
        return PPG;
    }

    public void setPPG(Double PPG) {
        this.PPG = PPG;
    }

    public Double getAPG() {
        return APG;
    }

    public void setAPG(Double APG) {
        this.APG = APG;
    }

    public Double getSPG() {
        return SPG;
    }

    public void setSPG(Double SPG) {
        this.SPG = SPG;
    }
}
