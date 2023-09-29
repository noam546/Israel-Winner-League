package com.example.springboot.Team;

import com.example.springboot.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, TeamKey> {

    List<Team> findByKeyName(String name);

}
