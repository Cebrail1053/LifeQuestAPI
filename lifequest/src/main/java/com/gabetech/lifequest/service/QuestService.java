package com.gabetech.lifequest.service;

import com.gabetech.lifequest.model.entity.Quest;

import java.util.List;

public interface QuestService {

    List<Quest> getAllQuests();

    Quest getQuestById(Long id);

    Quest createQuest(Quest quest);

    Quest updateQuest(Quest quest);

    void deleteQuest(Long id);
}
