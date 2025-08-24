package com.gabetech.lifequest.service;

import com.gabetech.lifequest.model.dto.AchievementRequestDTO;
import com.gabetech.lifequest.model.dto.AchievementResponseDTO;
import com.gabetech.lifequest.model.entity.Achievement;

import java.util.List;

public interface AchievementService {

    List<AchievementResponseDTO> getAllAchievements();

    AchievementResponseDTO createAchievement(AchievementRequestDTO requestDTO);
}
