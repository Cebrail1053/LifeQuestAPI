package com.gabetech.lifequest.service;

import com.gabetech.lifequest.model.entity.Achievement;

import java.util.List;

public interface AchievementService {

    List<Achievement> getAllAchievements();

    Achievement createAchievement(Achievement achievement);
}
