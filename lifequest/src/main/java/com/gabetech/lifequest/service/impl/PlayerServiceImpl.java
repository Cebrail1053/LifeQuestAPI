package com.gabetech.lifequest.service.impl;

import com.gabetech.lifequest.common.utils.MapperUtil;
import com.gabetech.lifequest.model.dto.PlayerRequestDTO;
import com.gabetech.lifequest.model.dto.PlayerResponseDTO;
import com.gabetech.lifequest.model.entity.Player;
import com.gabetech.lifequest.repository.PlayerRepository;
import com.gabetech.lifequest.service.PlayerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public List<PlayerResponseDTO> getAllPlayers() {
        return playerRepository.findAll().stream().map(MapperUtil::toDto).toList();
    }

    @Override
    public PlayerResponseDTO getPlayerById(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(RuntimeException::new);
        return MapperUtil.toDto(player, true);
    }

    @Override
    public PlayerResponseDTO createPlayer(PlayerRequestDTO requestDTO) {
        Player player = MapperUtil.toEntity(requestDTO);
        return MapperUtil.toDto(playerRepository.save(player));
    }

    @Override
    public PlayerResponseDTO updatePlayer(Long id, PlayerRequestDTO requestDTO) {
        Player updatedPlayer = playerRepository.findById(id).map(player -> {
            player.setName(requestDTO.name());
            player.setLevel(requestDTO.level());
            player.setXp(requestDTO.xp());
            return player;
        }).orElseThrow(() -> new RuntimeException("Player not found with id: " + id));
        return MapperUtil.toDto(playerRepository.save(updatedPlayer));
    }

    @Override
    public void deletePlayer(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new RuntimeException("Player not found with id: " + id);
        }
        playerRepository.deleteById(id);
    }
}
