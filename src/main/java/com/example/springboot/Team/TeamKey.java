package com.example.springboot.Team;

import jakarta.persistence.Embeddable;
import org.springframework.lang.NonNull;

import java.io.Serializable;

@Embeddable
public class TeamKey implements Serializable {

    @NonNull
    private String name;
    @NonNull
    private String leagueName;

    public TeamKey() {
    }

    public TeamKey(String name, String leagueName){
        this.name = name;
        this.leagueName = leagueName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }
}
