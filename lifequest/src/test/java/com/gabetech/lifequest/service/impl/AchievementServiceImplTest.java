package com.gabetech.lifequest.service.impl;

import com.gabetech.lifequest.builder.TestAchievementBuilder;
import com.gabetech.lifequest.common.utils.MapperUtil;
import com.gabetech.lifequest.domain.dto.AchievementRequestDTO;
import com.gabetech.lifequest.domain.dto.AchievementResponseDTO;
import com.gabetech.lifequest.model.entity.Achievement;
import com.gabetech.lifequest.repository.AchievementRepository;
import com.gabetech.lifequest.service.AchievementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AchievementServiceImplTest {

    private AchievementService achievementService;
    @Mock
    private AchievementRepository achievementRepository;

    @BeforeEach
    void setUp() {
        achievementService = new AchievementServiceImpl(achievementRepository);
    }

    @Test
    void testGetAllAchievements_ReturnMappedDTOs() {
        List<Achievement> achievements = getSampleAchievements();
        when(achievementRepository.findAll()).thenReturn(achievements);

        List<AchievementResponseDTO> result = achievementService.getAllAchievements();

        assertNotNull(result);
        assertEquals(achievements.size(), result.size());
        assertEquals(MapperUtil.toDto(achievements.getFirst()), result.getFirst());
    }

    @Test
    void testGetAllAchievements_ReturnEmptyList() {
        when(achievementRepository.findAll()).thenReturn(Collections.emptyList());

        List<AchievementResponseDTO> result = achievementService.getAllAchievements();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCreateAchievement_ReturnMappedDTO() {
        AchievementRequestDTO requestDTO = new AchievementRequestDTO(
              "First Steps",
              "Complete your first quest.",
              "quests.?[status == T(QuestStatus).COMPLETED].size() >= 1"
        );
        Achievement achievement = new TestAchievementBuilder()
              .withId(1)
              .withName(requestDTO.name())
              .withDescription(requestDTO.description())
              .withCondition(requestDTO.condition())
              .build();
        when(achievementRepository.save(any(Achievement.class))).thenReturn(achievement);
        AchievementResponseDTO expectedResponseDTO = MapperUtil.toDto(achievement);

        AchievementResponseDTO result = achievementService.createAchievement(requestDTO);

        assertNotNull(result);
        assertEquals(expectedResponseDTO, result);
    }

    @Test
    void testCreateAchievement_Failure() {
        // TODO: Still waiting for validation implementation
    }

    private List<Achievement> getSampleAchievements() {
        Achievement achievement1 = new TestAchievementBuilder()
              .withId(1)
              .withName("First Steps")
              .withDescription("Complete your first quest.")
              .withCondition("quests.?[status == T(QuestStatus).COMPLETED].size() >= 1")
              .build();

        Achievement achievement2 = new TestAchievementBuilder()
              .withId(2)
              .withName("Adventurer")
              .withDescription("Reach level 5.")
              .withCondition("level >= 5")
              .build();

        return List.of(achievement1, achievement2);
    }
}