package com.gabetech.lifequest.model.dto;

import com.gabetech.lifequest.model.entity.embed.PlayerQuestId;
import com.gabetech.lifequest.model.enums.QuestStatus;

public record PlayerQuestDTO(PlayerQuestId id, QuestStatus status) {
}
