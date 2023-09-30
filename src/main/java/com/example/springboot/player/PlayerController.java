package com.example.springboot.player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Player>> getAllPlayers(){
        try {
            List<Player> players = playerService.getAllPlayers();
            return ResponseEntity.ok(players);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "{playerId}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long playerId){
        try{
            Player player = playerService.getPlayerById(playerId);
            return ResponseEntity.ok(player);
        }catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(path = "{playerId}")
    public ResponseEntity<String> createNewPlayer(@PathVariable Long playerId, @RequestBody Player player){
        try{
            playerService.createNewPlayer(playerId, player);
            return ResponseEntity.ok("Player created successfully");
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Player already exists");
        }catch (InputMismatchException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(path = "{playerId}")
    public ResponseEntity<?> deletePlayer(@PathVariable Long playerId){
        try{
            playerService.deletePlayer(playerId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(path = "{playerId}")
    public ResponseEntity<String> updatePlayer(@PathVariable Long playerId,
                                               @RequestBody Player player){
        try{
            playerService.updatePlayer(playerId, player);
            return ResponseEntity.status(HttpStatus.OK).body("Player was updated successfully");
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
