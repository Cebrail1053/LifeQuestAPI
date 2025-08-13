package com.gabetech.lifequest.repository;

import com.gabetech.lifequest.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
