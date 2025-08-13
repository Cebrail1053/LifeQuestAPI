package com.gabetech.lifequest.model.entity;

import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class PlayerAchievement {

    private int playerId;
    private int achievementId;
    private LocalDateTime unlockedAt;
}
