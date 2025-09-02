package com.gabetech.lifequest.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gabetech.lifequest.model.entity.Inventory;
import com.gabetech.lifequest.model.entity.PlayerAchievement;
import com.gabetech.lifequest.model.entity.PlayerQuest;

import java.time.LocalDateTime;
import java.util.Set;

public record PlayerResponseDTO(int id, String name, int level, int xp, LocalDateTime createdAt,
                                @JsonInclude(JsonInclude.Include.NON_EMPTY)Set<PlayerAchievement> achievements,
                                @JsonInclude(JsonInclude.Include.NON_EMPTY)Set<Inventory> inventory,
                                @JsonInclude(JsonInclude.Include.NON_EMPTY)Set<PlayerQuest> quests) {
}
