package com.gabetech.lifequest.service.impl;

import com.gabetech.lifequest.common.validation.EntityExistenceValidator;
import com.gabetech.lifequest.model.entity.Inventory;
import com.gabetech.lifequest.model.entity.Item;
import com.gabetech.lifequest.model.entity.Player;
import com.gabetech.lifequest.model.entity.embed.PlayerInventoryId;
import com.gabetech.lifequest.repository.InventoryRepository;
import com.gabetech.lifequest.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final EntityExistenceValidator entityExistenceValidator;

    @Override
    public void addItemToInventory(Long playerId, Long itemId, int quantity) {
        if (!entityExistenceValidator.validateExists(playerId, Player.class) ||
        !entityExistenceValidator.validateExists(itemId, Item.class)) {
            throw new RuntimeException("Player or Item not found");
        }

        PlayerInventoryId id = new PlayerInventoryId(playerId.intValue(), itemId.intValue());
        inventoryRepository.findById(id).ifPresentOrElse(inventory -> {
            inventory.setQuantity(inventory.getQuantity() + quantity);
            inventoryRepository.save(inventory);
        }, () -> {
            Inventory inventory = new Inventory();
            inventory.setId(id);
            inventory.setQuantity(quantity);
            inventoryRepository.save(inventory);
        });
    }

    @Override
    public void removeItemFromInventory(Long playerId, Long itemId, int quantity) {
        PlayerInventoryId id = new PlayerInventoryId(playerId.intValue(), itemId.intValue());
        inventoryRepository.findById(id).ifPresentOrElse(inventory -> {
            if (inventory.getQuantity() < quantity) {
                // TODO: Handle this case gracefully
                throw new RuntimeException("Not enough items in inventory");
            }

            inventory.setQuantity(inventory.getQuantity() - quantity);

            if (inventory.getQuantity() == 0) {
                inventoryRepository.delete(inventory);
            } else {
                inventoryRepository.save(inventory);
            }
        }, () -> {
            throw new RuntimeException("Item not found in inventory");
        });
    }
}
