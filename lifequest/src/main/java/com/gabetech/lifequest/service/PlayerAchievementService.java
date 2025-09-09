package com.gabetech.lifequest.service;

import com.gabetech.lifequest.domain.QuestCompletionEvent;

public interface PlayerAchievementService {

    void handleAchievementUnlockForPlayer(QuestCompletionEvent event);
}
