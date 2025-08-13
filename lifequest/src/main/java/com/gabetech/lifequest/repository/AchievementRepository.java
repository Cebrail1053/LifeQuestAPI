package com.gabetech.lifequest.repository;

import com.gabetech.lifequest.model.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}
