package com.gabetech.lifequest.model.dto;

import com.gabetech.lifequest.model.entity.embed.PlayerAchievementId;

import java.time.LocalDateTime;

public record PlayerAchievementDTO(PlayerAchievementId id, LocalDateTime unlockedAt) {
}
