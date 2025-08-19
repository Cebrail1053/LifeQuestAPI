package com.gabetech.lifequest.service;

import com.gabetech.lifequest.model.dto.PlayerRequestDTO;
import com.gabetech.lifequest.model.dto.PlayerResponseDTO;

import java.util.List;

public interface PlayerService {

    List<PlayerResponseDTO> getAllPlayers();

    PlayerResponseDTO getPlayerById(Long id);

    PlayerResponseDTO createPlayer(PlayerRequestDTO requestDTO);

    PlayerResponseDTO updatePlayer(Long id, PlayerRequestDTO requestDTO);

    void deletePlayer(Long id);
}
