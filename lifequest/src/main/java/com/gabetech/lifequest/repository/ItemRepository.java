package com.gabetech.lifequest.repository;

import com.gabetech.lifequest.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
