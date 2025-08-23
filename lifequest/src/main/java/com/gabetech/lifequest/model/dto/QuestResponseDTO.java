package com.gabetech.lifequest.model.dto;

import com.gabetech.lifequest.model.entity.PlayerQuest;
import com.gabetech.lifequest.model.enums.Difficulty;

import java.util.Set;

public record QuestResponseDTO(int id, String title, String description, Difficulty difficulty, int xpReward,
                               Set<PlayerQuest> playerQuests) {
}
