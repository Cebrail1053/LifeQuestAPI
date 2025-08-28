package com.gabetech.lifequest.common.utils;

import com.gabetech.lifequest.model.dto.AchievementRequestDTO;
import com.gabetech.lifequest.model.dto.AchievementResponseDTO;
import com.gabetech.lifequest.model.dto.ItemRequestDTO;
import com.gabetech.lifequest.model.dto.ItemResponseDTO;
import com.gabetech.lifequest.model.dto.PlayerRequestDTO;
import com.gabetech.lifequest.model.dto.PlayerResponseDTO;
import com.gabetech.lifequest.model.dto.QuestRequestDTO;
import com.gabetech.lifequest.model.dto.QuestResponseDTO;
import com.gabetech.lifequest.model.entity.Achievement;
import com.gabetech.lifequest.model.entity.Inventory;
import com.gabetech.lifequest.model.entity.Item;
import com.gabetech.lifequest.model.entity.Player;
import com.gabetech.lifequest.model.entity.PlayerAchievement;
import com.gabetech.lifequest.model.entity.PlayerQuest;
import com.gabetech.lifequest.model.entity.Quest;
import com.gabetech.lifequest.model.enums.Difficulty;
import com.gabetech.lifequest.model.enums.ItemType;
import com.gabetech.lifequest.model.enums.Rarity;

import java.util.HashSet;
import java.util.Set;

public class MapperUtil {

    public static PlayerResponseDTO toDto(Player player) {
        return toDto(player, false);
    }

    public static PlayerResponseDTO toDto(Player player, boolean includeDetails) {
        if (player == null) {
            return null;
        }

        Set<PlayerAchievement> achievements = null;
        Set<Inventory> inventory = null;
        Set<PlayerQuest> quests = null;

        if (includeDetails) {
            achievements = player.getAchievements() != null ? new HashSet<>(player.getAchievements()) :
                  new HashSet<>();
            inventory = player.getInventory() != null ? new HashSet<>(player.getInventory()) :
                  new HashSet<>();
            quests = player.getQuests() != null ? new HashSet<>(player.getQuests()) : new HashSet<>();
        }

        return new PlayerResponseDTO(player.getId(), player.getName(), player.getLevel(), player.getXp(),
              player.getCreatedAt(), achievements, inventory, quests);
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

    public static AchievementResponseDTO toDto(Achievement achievement) {
        if (achievement == null) {
            return null;
        }
        return new AchievementResponseDTO(achievement.getId(), achievement.getName(),
              achievement.getDescription());
    }

    public static Achievement toEntity(AchievementRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        Achievement achievement = new Achievement();
        achievement.setName(requestDTO.name());
        achievement.setDescription(requestDTO.description());
        achievement.setCondition(requestDTO.condition());
        return achievement;
    }

    public static QuestResponseDTO toDto(Quest quest) {
        if (quest == null) {
            return null;
        }
        return new QuestResponseDTO(quest.getId(), quest.getTitle(), quest.getDescription(),
              quest.getDifficulty(), quest.getXpReward());
    }

    public static Quest toEntity(QuestRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        Quest quest = new Quest();
        quest.setTitle(requestDTO.title());
        quest.setDescription(requestDTO.description());
        quest.setDifficulty(Difficulty.valueOf(requestDTO.difficulty()));
        quest.setXpReward(requestDTO.xpReward());
        return quest;
    }

    public static ItemResponseDTO toDto(Item item) {
        if (item == null) {
            return null;
        }
        return new ItemResponseDTO(item.getId(), item.getName(), item.getType().name(),
              item.getRarity().name());
    }

    public static Item toEntity(ItemRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        Item item = new Item();
        item.setName(requestDTO.name());
        item.setType(ItemType.valueOf(requestDTO.type()));
        item.setRarity(Rarity.valueOf(requestDTO.rarity()));
        return item;
    }
}
