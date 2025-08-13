package com.gabetech.lifequest.repository;

import com.gabetech.lifequest.model.entity.PlayerAchievement;
import com.gabetech.lifequest.model.entity.embed.PlayerAchievementId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerAchievementRepository extends JpaRepository<PlayerAchievement, PlayerAchievementId> {
}
