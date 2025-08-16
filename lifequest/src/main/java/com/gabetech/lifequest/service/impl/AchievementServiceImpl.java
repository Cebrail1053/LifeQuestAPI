package com.gabetech.lifequest.service.impl;

import com.gabetech.lifequest.model.entity.Achievement;
import com.gabetech.lifequest.repository.AchievementRepository;
import com.gabetech.lifequest.service.AchievementService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;

    @Override
    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    @Override
    @Transactional
    public Achievement createAchievement(Achievement achievement) {
        return achievementRepository.save(achievement);
    }
}
