package com.gabetech.lifequest.model.entity;

import com.gabetech.lifequest.model.enums.QuestStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class PlayerQuest {

    private int playerId;
    private int questId;
    @Enumerated(value = EnumType.STRING)
    private QuestStatus status;
    private LocalDateTime completedAt;
}
