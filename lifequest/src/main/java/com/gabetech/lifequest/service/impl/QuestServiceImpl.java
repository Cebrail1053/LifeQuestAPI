package com.gabetech.lifequest.service.impl;

import com.gabetech.lifequest.common.utils.MapperUtil;
import com.gabetech.lifequest.domain.dto.QuestRequestDTO;
import com.gabetech.lifequest.domain.dto.QuestResponseDTO;
import com.gabetech.lifequest.model.entity.Quest;
import com.gabetech.lifequest.model.enums.Difficulty;
import com.gabetech.lifequest.repository.QuestRepository;
import com.gabetech.lifequest.service.QuestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestServiceImpl implements QuestService {

    private final QuestRepository questRepository;

    @Override
    public List<QuestResponseDTO> getAllQuests(String difficultyFilter) {
        if(StringUtils.hasText(difficultyFilter)) {
            Difficulty difficulty;
            try {
                difficulty = Difficulty.valueOf(difficultyFilter.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid difficulty filter: " + difficultyFilter);
            }
            log.info("Filtering all quests by difficulty: {}", difficulty);
            return questRepository.findAll().stream()
                  .filter(quest -> quest.getDifficulty().equals(difficulty))
                  .map(MapperUtil::toDto)
                  .toList();
        }
        log.info("Retrieving all quests without difficulty filter");
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
            quest.setDifficulty(Difficulty.valueOf(requestDTO.difficulty().toUpperCase()));
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
