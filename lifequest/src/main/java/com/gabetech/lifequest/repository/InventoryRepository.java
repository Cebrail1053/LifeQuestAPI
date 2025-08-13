package com.gabetech.lifequest.repository;

import com.gabetech.lifequest.model.entity.Inventory;
import com.gabetech.lifequest.model.entity.embed.PlayerInventoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, PlayerInventoryId> {
}
