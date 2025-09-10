package com.gabetech.lifequest.builder;

import com.gabetech.lifequest.model.entity.Achievement;

import java.time.LocalDateTime;

public class TestAchievementBuilder {
    private Integer id = 1;
    private String name;
    private String description;
    private String condition;
    private LocalDateTime createdAt = LocalDateTime.now();

    public TestAchievementBuilder() {
        this.name = "Test Achievement";
        this.description = "This is a test achievement.";
        this.condition = "level >= 2";
    }

    public TestAchievementBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public TestAchievementBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TestAchievementBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public TestAchievementBuilder withCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public TestAchievementBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Achievement build() {
        Achievement achievement = new Achievement();
        achievement.setId(this.id);
        achievement.setName(this.name);
        achievement.setDescription(this.description);
        achievement.setCondition(this.condition);
        achievement.setCreatedAt(this.createdAt);
        return achievement;
    }
}
