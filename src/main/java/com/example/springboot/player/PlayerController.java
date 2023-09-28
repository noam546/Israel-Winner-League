package com.example.springboot.player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping(path = "api/v1/player")
public class PlayerController {

    private final PlayerService playerService;

    //dependency injection
    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getAllPlayers(){
        return playerService.getAllPlayers();
    }

    @GetMapping(path = "{playerId}")
    public Player getPlayerById(@PathVariable Long playerId){
        return playerService.getPlayerById(playerId);
    }

    @PostMapping(path = "{playerId}")
    public void addNewPlayer(@PathVariable Long playerId, @RequestBody Player player){
        player.setId(playerId);
        playerService.addNewPlayer(player);
    }

    @DeleteMapping(path = "{playerId}")
    public void deletePlayer(@PathVariable Long playerId){
        playerService.deletePlayer(playerId);
    }

    @PutMapping(path = "{playerId}")
    public void updatePlayer(@PathVariable Long playerId,
                             @RequestBody Player player){
        playerService.updatePlayer(playerId, player);
    }


}
