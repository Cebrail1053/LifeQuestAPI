package com.gabetech.lifequest.service.impl;

import com.gabetech.lifequest.common.validation.EntityExistenceValidator;
import com.gabetech.lifequest.domain.QuestCompletionEvent;
import com.gabetech.lifequest.model.entity.Player;
import com.gabetech.lifequest.model.entity.PlayerQuest;
import com.gabetech.lifequest.model.entity.Quest;
import com.gabetech.lifequest.model.entity.embed.PlayerQuestId;
import com.gabetech.lifequest.model.enums.QuestStatus;
import com.gabetech.lifequest.repository.PlayerQuestRepository;
import com.gabetech.lifequest.service.PlayerQuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class PlayerQuestServiceImpl implements PlayerQuestService {

    private final ApplicationEventPublisher publisher;
    private final PlayerQuestRepository playerQuestRepository;
    private final EntityExistenceValidator entityExistenceValidator;

    @Override
    public void assignQuestToPlayer(Long playerId, Long questId) {
        if (!entityExistenceValidator.validateExists(playerId, Player.class) ||
              !entityExistenceValidator.validateExists(questId, Quest.class)) {
            throw new RuntimeException("Player or Quest not found");
        }

        PlayerQuestId id = new PlayerQuestId(playerId.intValue(), questId.intValue());
        if (playerQuestRepository.existsById(id)) {
            throw new RuntimeException("Quest already assigned to player");
        }

        PlayerQuest playerQuest = assignPlayerQuest(id);
        playerQuestRepository.save(playerQuest);
    }

    @Override
    public void completeQuestForPlayer(Long playerId, Long questId) {
        PlayerQuestId id = new PlayerQuestId(playerId.intValue(), questId.intValue());

        playerQuestRepository.findById(id).ifPresentOrElse(playerQuest -> {
            playerQuest.setStatus(QuestStatus.COMPLETED);
            playerQuest.setCompletedAt(LocalDateTime.now());
            playerQuestRepository.save(playerQuest);

            QuestCompletionEvent event = new QuestCompletionEvent(playerQuest.getPlayer(),
                  playerQuest.getQuest().getXpReward());

            publisher.publishEvent(event);
        }, () -> {
            throw new RuntimeException("PlayerQuest not found");
        });
    }

    private PlayerQuest assignPlayerQuest(PlayerQuestId id) {
        PlayerQuest playerQuest = new PlayerQuest();
        playerQuest.setId(id);
        playerQuest.setStatus(QuestStatus.ASSIGNED);
        return playerQuest;
    }
}
