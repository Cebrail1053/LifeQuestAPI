package com.gabetech.lifequest.domain;

import lombok.Value;

@Value
public class QuestCompletionEvent {
    int playerLevel;
    int playerXp;
    int rewardXp;
    // TODO: Add required fields for achievement check
}
