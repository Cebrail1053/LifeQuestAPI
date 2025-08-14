package com.gabetech.lifequest.repository;

import com.gabetech.lifequest.model.entity.PlayerAchievement;
import com.gabetech.lifequest.model.entity.embed.PlayerAchievementId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerAchievementRepository extends JpaRepository<PlayerAchievement, PlayerAchievementId> {
}
