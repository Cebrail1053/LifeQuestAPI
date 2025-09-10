package com.gabetech.lifequest.common.helper;

import com.gabetech.lifequest.model.entity.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GameLogicHelper {

    public Player handlePlayerLevelUp(Player player, int xpReward) {
        int totalXp = player.getXp() + xpReward;
        int level = player.getLevel();
        int xpForNextLevel = calculateXpForNextLevel(level);
        log.info("event = \"Calculating XP for Level Up\" currentLevel={} currentXp={} xpReward={} totalXp={} xpForNextLevel={}",
                level, player.getXp(), xpReward, totalXp, xpForNextLevel);

        if (totalXp >= xpForNextLevel) {
            player.setLevel(++level);
            log.info("event = \"Player leveled up! New level: {}\"", level);
        }

        player.setXp(totalXp);
        return player;
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
