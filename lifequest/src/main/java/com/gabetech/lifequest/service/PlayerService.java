package com.gabetech.lifequest.service;

import com.gabetech.lifequest.model.entity.Player;

import java.util.List;

public interface PlayerService {

    List<Player> getAllPlayers();

    Player getPlayerById(int id);

    Player createPlayer(Player player);

    Player updatePlayer(Player player);

    boolean deletePlayer(int id);
}
