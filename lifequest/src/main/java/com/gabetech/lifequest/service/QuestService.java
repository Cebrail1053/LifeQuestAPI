package com.gabetech.lifequest.service;

import com.gabetech.lifequest.domain.dto.QuestRequestDTO;
import com.gabetech.lifequest.domain.dto.QuestResponseDTO;

import java.util.List;

public interface QuestService {

    List<QuestResponseDTO> getAllQuests(String difficulty);

    QuestResponseDTO getQuestById(Long id);

    QuestResponseDTO createQuest(QuestRequestDTO requestDTO);

    QuestResponseDTO updateQuest(Long id, QuestRequestDTO requestDTO);

    void deleteQuest(Long id);
}
