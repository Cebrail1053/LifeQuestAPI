package com.gabetech.lifequest.service;

import com.gabetech.lifequest.model.entity.Player;

import java.util.List;

public interface PlayerService {

    List<Player> getAllPlayers();

    Player getPlayerById(Long id);

    Player createPlayer(Player player);

    Player updatePlayer(Player player);

    void deletePlayer(Long id);
}
