package com.gabetech.lifequest.repository;

import com.gabetech.lifequest.model.entity.Achievement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
public class AchievementRepositoryTest {

    @Autowired
    private AchievementRepository achievementRepository;

    @Test
    void testAchievementRepository_findAll_Empty() {
        assertNotNull(achievementRepository);

        List<Achievement> achievements = achievementRepository.findAll();
        assertTrue(achievements.isEmpty());
    }

    @Test
    void testAchievementRepository_saveAndFindAll() {
        assertNotNull(achievementRepository);

        Achievement achievement = new Achievement();
        achievement.setName("First Blood");
        achievement.setDescription("Complete your first quest");
        achievement.setCondition("quests.?[status == T(QuestStatus).COMPLETED].size() >= 1");

        Achievement expectedAchievement = achievementRepository.save(achievement);
        List<Achievement> achievements = achievementRepository.findAll();

        assertNotNull(achievements);
        assertEquals(1, achievements.size());
        assertEquals("First Blood", achievements.getFirst().getName());
        assertEquals(expectedAchievement.getId(), achievements.getFirst().getId());
        assertEquals(expectedAchievement.getCreatedAt(), achievements.getFirst().getCreatedAt());
    }
}
