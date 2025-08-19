package com.gabetech.lifequest.common.utils;

import com.gabetech.lifequest.model.dto.PlayerRequestDTO;
import com.gabetech.lifequest.model.dto.PlayerResponseDTO;
import com.gabetech.lifequest.model.entity.Inventory;
import com.gabetech.lifequest.model.entity.Player;
import com.gabetech.lifequest.model.entity.PlayerAchievement;
import com.gabetech.lifequest.model.entity.PlayerQuest;

import java.util.Set;

public class MapperUtil {

    public static PlayerResponseDTO toDto(Player player) {
        return toDto(player, false);
    }

    public static PlayerResponseDTO toDto(Player player, boolean includeDetails) {
        Set<PlayerAchievement> achievements = null;
        Set<Inventory> inventory = null;
        Set<PlayerQuest> quests = null;

        if (includeDetails) {
            achievements = player.getAchievements();
            inventory = player.getInventorySet();
            quests = player.getQuests();
        }

        return new PlayerResponseDTO(
              player.getId(),
              player.getName(),
              player.getLevel(),
              player.getXp(),
              player.getCreatedAt(),
              achievements,
              inventory,
              quests
        );
    }

    public static Player toEntity(PlayerRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        Player player = new Player();
        player.setName(requestDTO.name());
        player.setLevel(requestDTO.level());
        player.setXp(requestDTO.xp());
        return player;
    }
}
