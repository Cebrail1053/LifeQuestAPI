package com.gabetech.lifequest.builder;

import com.gabetech.lifequest.model.entity.Inventory;
import com.gabetech.lifequest.model.entity.Player;
import com.gabetech.lifequest.model.entity.PlayerAchievement;
import com.gabetech.lifequest.model.entity.PlayerQuest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

public class TestPlayerBuilder {
    private int id = 1;
    private String name;
    private int level;
    private int xp;
    private LocalDateTime createdAt = LocalDateTime.now();
    private Set<PlayerAchievement> achievements = Collections.emptySet();
    private Set<Inventory> inventory = Collections.emptySet();
    private Set<PlayerQuest> quests = Collections.emptySet();

    public TestPlayerBuilder() {
        this.name = "TestPlayer";
        this.level = 1;
        this.xp = 50;
    }

    public TestPlayerBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public TestPlayerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TestPlayerBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    public TestPlayerBuilder withXp(int xp) {
        this.xp = xp;
        return this;
    }

    public TestPlayerBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public TestPlayerBuilder withAchievements(Set<PlayerAchievement> achievements) {
        this.achievements = achievements;
        return this;
    }

    public TestPlayerBuilder withInventory(Set<Inventory> inventory) {
        this.inventory = inventory;
        return this;
    }

    public TestPlayerBuilder withQuests(Set<PlayerQuest> quests) {
        this.quests = quests;
        return this;
    }

    public Player build() {
        Player player = new Player();
        player.setId(this.id);
        player.setName(this.name);
        player.setLevel(this.level);
        player.setXp(this.xp);
        player.setCreatedAt(this.createdAt);
        player.setAchievements(this.achievements);
        player.setInventory(this.inventory);
        player.setQuests(this.quests);
        return player;
    }
}
