package com.gabetech.lifequest.service.impl;

import com.gabetech.lifequest.common.utils.MapperUtil;
import com.gabetech.lifequest.model.dto.QuestRequestDTO;
import com.gabetech.lifequest.model.dto.QuestResponseDTO;
import com.gabetech.lifequest.model.entity.Quest;
import com.gabetech.lifequest.model.enums.Difficulty;
import com.gabetech.lifequest.repository.QuestRepository;
import com.gabetech.lifequest.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestServiceImpl implements QuestService {

    private final QuestRepository questRepository;

    @Override
    public List<QuestResponseDTO> getAllQuests() {
        return questRepository.findAll().stream().map(MapperUtil::toDto).toList();
    }

    @Override
    public QuestResponseDTO getQuestById(Long id) {
        return questRepository.findById(id).map(MapperUtil::toDto).orElseThrow(RuntimeException::new);
    }

    @Override
    public QuestResponseDTO createQuest(QuestRequestDTO requestDTO) {
        Quest quest = MapperUtil.toEntity(requestDTO);
        return MapperUtil.toDto(questRepository.save(quest));
    }

    @Override
    public QuestResponseDTO updateQuest(Long id, QuestRequestDTO requestDTO) {
        Quest updatedQuest = questRepository.findById(id).map(quest -> {
            quest.setTitle(requestDTO.title());
            quest.setDescription(requestDTO.description());
            quest.setDifficulty(Difficulty.valueOf(requestDTO.difficulty()));
            quest.setXpReward(requestDTO.xpReward());
            return quest;
        }).orElseThrow(() -> new RuntimeException("Quest not found with id: " + id));
        return MapperUtil.toDto(questRepository.save(updatedQuest));
    }

    @Override
    public void deleteQuest(Long id) {
        if (!questRepository.existsById(id)) {
            throw new RuntimeException("Quest not found with id: " + id);
        }
        questRepository.deleteById(id);
    }
}
