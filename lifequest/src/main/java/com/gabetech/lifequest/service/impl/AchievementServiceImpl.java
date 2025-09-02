package com.gabetech.lifequest.service.impl;

import com.gabetech.lifequest.common.utils.MapperUtil;
import com.gabetech.lifequest.domain.dto.AchievementRequestDTO;
import com.gabetech.lifequest.domain.dto.AchievementResponseDTO;
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
    public List<AchievementResponseDTO> getAllAchievements() {
        return achievementRepository.findAll().stream().map(MapperUtil::toDto).toList();
    }

    @Override
    @Transactional
    public AchievementResponseDTO createAchievement(AchievementRequestDTO requestDTO) {
        Achievement achievement = MapperUtil.toEntity(requestDTO);
        return MapperUtil.toDto(achievementRepository.save(achievement));
    }
}
