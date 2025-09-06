package com.gabetech.lifequest.service.impl;

import com.gabetech.lifequest.config.context.PlayerEvaluationContext;
import com.gabetech.lifequest.model.entity.Achievement;
import com.gabetech.lifequest.model.entity.Player;
import com.gabetech.lifequest.model.entity.PlayerAchievement;
import com.gabetech.lifequest.model.entity.embed.PlayerAchievementId;
import com.gabetech.lifequest.model.enums.QuestStatus;
import com.gabetech.lifequest.repository.AchievementRepository;
import com.gabetech.lifequest.repository.PlayerRepository;
import com.gabetech.lifequest.service.PlayerAchievementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PlayerAchievementServiceImpl implements PlayerAchievementService {

    private final PlayerRepository playerRepository;
    private final AchievementRepository achievementRepository;

    @Override
    public void unlockAchievementForPlayer(Long playerId) {
        // TODO: Find out if this should be an async event or a synchronous event
        Player player = playerRepository.findById(playerId)
              .orElseThrow(() -> new RuntimeException("Player not found"));

        List<Achievement> playerAchievements = player.getAchievements().stream()
              .map(PlayerAchievement::getAchievement).toList();

        List<Achievement> achievements = achievementRepository.findAll().stream()
              .filter(achievement -> !playerAchievements.contains(achievement))
              .toList();

        if (!achievements.isEmpty()) {
            achievements.forEach(achievement -> {
                if (isConditionMet(player, achievement)) {
                    PlayerAchievementId id = new PlayerAchievementId(playerId.intValue(), achievement.getId());
                    PlayerAchievement playerAchievement = new PlayerAchievement();
                    playerAchievement.setId(id);
                    playerAchievement.setPlayer(player);
                    playerAchievement.setAchievement(achievement);
                    playerAchievement.setUnlockedAt(LocalDateTime.now());
                    player.getAchievements().add(playerAchievement);
                }
            });
            playerRepository.save(player);
        }
    }

    private boolean isConditionMet(Player player, Achievement achievement) {
        ExpressionParser parser = new SpelExpressionParser();
        PlayerEvaluationContext context = new PlayerEvaluationContext(player);
        Boolean result = parser.parseExpression(achievement.getCondition()).getValue(context, Boolean.class);
        return Boolean.TRUE.equals(result);
    }
}
