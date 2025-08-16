package com.gabetech.lifequest.service.impl;

import com.gabetech.lifequest.model.entity.Player;
import com.gabetech.lifequest.repository.PlayerRepository;
import com.gabetech.lifequest.service.PlayerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    @Transactional
    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    @Transactional
    public Player updatePlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    @Transactional
    public void deletePlayer(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new RuntimeException("Player not found with id: " + id);
        }
        playerRepository.deleteById(id);
    }
}
