package com.gabetech.lifequest.repository;

import com.gabetech.lifequest.model.entity.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestRepository extends JpaRepository<Quest, Long> {
}
