package com.gabetech.lifequest.model.dto;

import com.gabetech.lifequest.model.enums.Difficulty;

public record QuestDTO(int id, String title, String description, Difficulty difficulty, int xpReward) {
}
