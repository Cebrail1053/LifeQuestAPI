package com.gabetech.lifequest.repository;

import com.gabetech.lifequest.model.entity.PlayerQuest;
import com.gabetech.lifequest.model.entity.embed.PlayerQuestId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerQuestRepository extends JpaRepository<PlayerQuest, PlayerQuestId> {
}
