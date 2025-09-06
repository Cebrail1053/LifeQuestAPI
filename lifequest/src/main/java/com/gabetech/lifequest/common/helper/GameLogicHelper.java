package com.gabetech.lifequest.common.helper;

import com.gabetech.lifequest.domain.QuestCompletionEvent;
import com.gabetech.lifequest.model.entity.Player;
import com.gabetech.lifequest.service.PlayerAchievementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
@Slf4j
public class GameLogicHelper {

    private final PlayerAchievementService playerAchievementService;

    @EventListener
    public void handlePlayerLevelUpEvent(QuestCompletionEvent event) {
        log.info("event = \"QuestCompletionEvent Received - handling level up calculations:\" {}", event);
        Player player = event.getPlayer();
        int xpReward = event.getRewardXp();

        addXpAndHandleLevelUp(player, xpReward);
        playerAchievementService.unlockAchievementForPlayer(player);
    }

    private void addXpAndHandleLevelUp(Player player, int xpReward) {
        int totalXp = player.getXp() + xpReward;
        int level = player.getLevel();
        int xpForNextLevel = calculateXpForNextLevel(level);

        if (totalXp >= xpForNextLevel) {
            player.setLevel(++level);
            log.info("event = \"Player leveled up! New level: {}\"", level);
        }

        player.setXp(totalXp);
    }

    private int calculateXpForNextLevel(int level) {
        final int XP_MULTIPLIER = 5;
        final int XP_INCREMENT_PER_LEVEL = 65;
        final int INITIAL_XP_FOR_NEXT_LEVEL = 100;

        double result = XP_MULTIPLIER * Math.pow(level - 1, 2);
        result += XP_INCREMENT_PER_LEVEL * (level - 1) + INITIAL_XP_FOR_NEXT_LEVEL;
        return (int) Math.floor(result);
    }
}
