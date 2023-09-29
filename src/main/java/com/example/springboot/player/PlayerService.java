package com.example.springboot.player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers(){
        try{
            return playerRepository.findAll();
        }catch (Exception e){
            throw new IllegalStateException("Error occurred while fetching teams", e);
        }
    }

    public void addNewPlayer(Long playerId, Player player){
        Optional<Player> foundPlayer = playerRepository.findById(playerId);
        if(foundPlayer.isPresent()){
            throw new IllegalStateException("player exists");
        }
        player.setId(playerId);
        playerRepository.save(player);
    }


    public void deletePlayer(Long playerId) {
        boolean playerExists = playerRepository.existsById(playerId);
        if(!playerExists){
            throw new IllegalStateException("player does not exist");
        }
        playerRepository.deleteById(playerId);
    }

    @Transactional
    public void updatePlayer(Long playerId,
                                  Player updatedPlayer) {
        Player existingPlayer = playerRepository.findById(playerId).
                orElseThrow(()-> new IllegalStateException("player does not exist"));
        updatePlayerStats(existingPlayer, updatedPlayer);
    }

    public Player getPlayerById(Long playerId) {
        Player existingPlayer = playerRepository.findById(playerId).
                orElseThrow(()-> new IllegalStateException("player does not exist"));
        return existingPlayer;
    }

    private void updatePlayerStats(Player existingPlayer, Player updatedPlayer){
        if(updatedPlayer.getName() != null){
            existingPlayer.setName(updatedPlayer.getName());
        }
        if(updatedPlayer.getPosition() != null){
            existingPlayer.setPosition(updatedPlayer.getPosition());
        }
        if(updatedPlayer.getHeight() != 0.0){
            existingPlayer.setHeight(updatedPlayer.getHeight());
        }
        if(updatedPlayer.getDob() != null){
            existingPlayer.setDob(updatedPlayer.getDob());
        }
        if(updatedPlayer.getNationality() != null){
            existingPlayer.setNationality(updatedPlayer.getNationality());
        }
    }
}
