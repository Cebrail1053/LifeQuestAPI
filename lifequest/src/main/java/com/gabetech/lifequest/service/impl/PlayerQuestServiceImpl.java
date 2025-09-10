package com.gabetech.lifequest.service.impl;

import com.gabetech.lifequest.domain.QuestCompletionEvent;
import com.gabetech.lifequest.model.entity.Player;
import com.gabetech.lifequest.model.entity.PlayerQuest;
import com.gabetech.lifequest.model.entity.Quest;
import com.gabetech.lifequest.model.entity.embed.PlayerQuestId;
import com.gabetech.lifequest.model.enums.QuestStatus;
import com.gabetech.lifequest.repository.PlayerQuestRepository;
import com.gabetech.lifequest.repository.PlayerRepository;
import com.gabetech.lifequest.repository.QuestRepository;
import com.gabetech.lifequest.service.PlayerQuestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PlayerQuestServiceImpl implements PlayerQuestService {

    private final ApplicationEventPublisher publisher;
    private final PlayerRepository playerRepository;
    private final QuestRepository questRepository;
    private final PlayerQuestRepository playerQuestRepository;

    @Override
    public void assignQuestToPlayer(Long playerId, Long questId) {
        log.info("Validating player and item existence, playerId={}, questId={}", playerId, questId);
        Player player = playerRepository.findById(playerId)
              .orElseThrow(() -> new RuntimeException("Player not found"));
        Quest quest = questRepository.findById(questId)
              .orElseThrow(() -> new RuntimeException("Quest not found"));

        PlayerQuestId id = new PlayerQuestId(playerId.intValue(), questId.intValue());
        log.info("Player and quest validated, proceeding to assign quest to player with id={}", id);
        if (playerQuestRepository.existsById(id)) {
            throw new RuntimeException("Quest already assigned to player"); // TODO custom exception here
        }

        PlayerQuest playerQuest = assignPlayerQuest(id, player, quest);
        playerQuestRepository.save(playerQuest);
    }

    @Override
    public void completeQuestForPlayer(Long playerId, Long questId) {
        PlayerQuestId id = new PlayerQuestId(playerId.intValue(), questId.intValue());

        playerQuestRepository.findById(id).ifPresentOrElse(playerQuest -> {
            playerQuest.setStatus(QuestStatus.COMPLETED);
            playerQuest.setCompletedAt(LocalDateTime.now());
            playerQuestRepository.save(playerQuest);

            log.info("event=\"Quest Completed\" playerQuestId={}", id);

            QuestCompletionEvent event = new QuestCompletionEvent(playerQuest.getPlayer(),
                  playerQuest.getQuest().getXpReward());

            publisher.publishEvent(event);
        }, () -> {
            throw new RuntimeException("PlayerQuest not found"); // TODO custom exception here
        });
    }

    private PlayerQuest assignPlayerQuest(PlayerQuestId id, Player player, Quest quest) {
        PlayerQuest playerQuest = new PlayerQuest();
        playerQuest.setId(id);
        playerQuest.setPlayer(player);
        playerQuest.setQuest(quest);
        playerQuest.setStatus(QuestStatus.ASSIGNED);
        return playerQuest;
    }
}
