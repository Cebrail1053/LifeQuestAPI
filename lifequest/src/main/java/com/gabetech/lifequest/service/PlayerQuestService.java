package com.gabetech.lifequest.service;

public interface PlayerQuestService {

    void assignQuestToPlayer(Long playerId, Long questId);

    void completeQuestForPlayer(Long playerId, Long questId);
}
