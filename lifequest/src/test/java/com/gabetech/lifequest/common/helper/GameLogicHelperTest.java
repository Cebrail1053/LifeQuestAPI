package com.gabetech.lifequest.common.helper;

import com.gabetech.lifequest.builder.TestPlayerBuilder;
import com.gabetech.lifequest.model.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameLogicHelperTest {

    private GameLogicHelper gameLogicHelper;

    @BeforeEach
    void setUp() {
        gameLogicHelper = new GameLogicHelper();
    }

    @Test
    void testHandlePlayerLevelUp_NoLevelUp() {
        Player player = new TestPlayerBuilder().build();

        gameLogicHelper.handlePlayerLevelUp(player, 25);

        assertEquals(1, player.getLevel());
        assertEquals(75, player.getXp());
    }

    @Test
    void testHandlePlayerLevelUp_MultipleLevelUps() {
        Player player = new TestPlayerBuilder().build();

        gameLogicHelper.handlePlayerLevelUp(player, 500);

        assertEquals(4, player.getLevel());
        assertEquals(550, player.getXp());
    }

    @Test
    void testHandlePlayerLevelUp_LeveledUpEdgeCase() {
        Player player = new TestPlayerBuilder().build();

        gameLogicHelper.handlePlayerLevelUp(player, 50);

        assertEquals(2, player.getLevel());
        assertEquals(100, player.getXp());
    }

    @Test
    void testHandlePlayerLevelUp_HigherStartingLevel() {
        Player player = new TestPlayerBuilder()
              .withLevel(4)
              .withXp(550)
              .build();

        gameLogicHelper.handlePlayerLevelUp(player, 240);

        assertEquals(6, player.getLevel());
        assertEquals(790, player.getXp());
    }
}