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
        return playerRepository.findAll();
    }

    public void addNewPlayer(Player player){
        Optional<Player> foundPlayer = playerRepository.findById(player.getId());
        if(foundPlayer.isPresent()){
            throw new IllegalStateException("player exists");
        }
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
        Player player = playerRepository.findById(playerId).
                orElseThrow(()-> new IllegalStateException("player does not exist"));
        if(updatedPlayer.getName() != null){
            player.setName(updatedPlayer.getName());
        }
        if(updatedPlayer.getPosition() != null){
            player.setPosition(updatedPlayer.getPosition());
        }
        if(updatedPlayer.getHeight() != 0.0){
            player.setHeight(updatedPlayer.getHeight());
        }
        if(updatedPlayer.getDob() != null){
            player.setDob(updatedPlayer.getDob());
        }
    }

    public Player getPlayerById(Long playerId) {
        Player player = playerRepository.findById(playerId).
                orElseThrow(()-> new IllegalStateException("player does not exist"));
        return player;
    }
}
