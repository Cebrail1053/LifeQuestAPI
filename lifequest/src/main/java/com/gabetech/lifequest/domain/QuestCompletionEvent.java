package com.gabetech.lifequest.domain;

import com.gabetech.lifequest.model.entity.Player;
import lombok.Value;

@Value
public class QuestCompletionEvent {
    Player player;
    int rewardXp;
}
