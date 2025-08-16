package com.gabetech.lifequest.service.impl;

import com.gabetech.lifequest.model.entity.Quest;
import com.gabetech.lifequest.repository.QuestRepository;
import com.gabetech.lifequest.service.QuestService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestServiceImpl implements QuestService {

    private final QuestRepository questRepository;

    @Override
    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }

    @Override
    public Quest getQuestById(Long id) {
        return questRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    @Transactional
    public Quest createQuest(Quest quest) {
        return questRepository.save(quest);
    }

    @Override
    @Transactional
    public Quest updateQuest(Quest quest) {
        return questRepository.save(quest);
    }

    @Override
    @Transactional
    public void deleteQuest(Long id) {
        if (!questRepository.existsById(id)) {
            throw new RuntimeException("Quest not found with id: " + id);
        }
        questRepository.deleteById(id);
    }
}
