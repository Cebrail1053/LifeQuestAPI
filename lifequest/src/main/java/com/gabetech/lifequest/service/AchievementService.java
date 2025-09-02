package com.gabetech.lifequest.service;

import com.gabetech.lifequest.domain.dto.AchievementRequestDTO;
import com.gabetech.lifequest.domain.dto.AchievementResponseDTO;

import java.util.List;

public interface AchievementService {

    List<AchievementResponseDTO> getAllAchievements();

    AchievementResponseDTO createAchievement(AchievementRequestDTO requestDTO);
}
