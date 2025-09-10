package com.gabetech.lifequest.service.impl;

import com.gabetech.lifequest.model.entity.Inventory;
import com.gabetech.lifequest.model.entity.Item;
import com.gabetech.lifequest.model.entity.Player;
import com.gabetech.lifequest.model.entity.embed.PlayerInventoryId;
import com.gabetech.lifequest.repository.InventoryRepository;
import com.gabetech.lifequest.repository.ItemRepository;
import com.gabetech.lifequest.repository.PlayerRepository;
import com.gabetech.lifequest.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final PlayerRepository playerRepository;
    private final ItemRepository itemRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public void addItemToInventory(Long playerId, Long itemId, int quantity) {
        log.info("Validating player and item existence, playerId={}, itemId={}", playerId, itemId);
        Player player = playerRepository.findById(playerId)
              .orElseThrow(() -> new RuntimeException("Player not found"));
        Item item = itemRepository.findById(itemId)
              .orElseThrow(() -> new RuntimeException("Item not found"));

        PlayerInventoryId id = new PlayerInventoryId(playerId.intValue(), itemId.intValue());
        log.info("Player and item validated, proceeding to add item to inventory with id={}", id);
        inventoryRepository.findById(id).ifPresentOrElse(inventory -> {
            inventory.setQuantity(inventory.getQuantity() + quantity);
            inventoryRepository.save(inventory);
            log.info("Inventory item {} updated, newQuantity={}", id, inventory.getQuantity());
        }, () -> {
            Inventory inventory = new Inventory();
            inventory.setId(id);
            inventory.setPlayer(player);
            inventory.setItem(item);
            inventory.setQuantity(quantity);
            inventoryRepository.save(inventory);
            log.info("Created new inventory record, inventoryId={}, quantity={}", id, quantity);
        });
    }

    @Override
    public void removeItemFromInventory(Long playerId, Long itemId, int quantity) {
        PlayerInventoryId id = new PlayerInventoryId(playerId.intValue(), itemId.intValue());
        inventoryRepository.findById(id).ifPresentOrElse(inventory -> {
            if (inventory.getQuantity() < quantity) {
                log.warn("Requested quantity exceeds available quantity, inventoryId={}, " +
                      "availableQuantity={}, requestedQuantity={}", id, inventory.getQuantity(), quantity);
                return;
            }

            inventory.setQuantity(inventory.getQuantity() - quantity);

            if (inventory.getQuantity() == 0) {
                log.info("Quantity is zero after removal, deleting inventory record with id={}", id);
                inventoryRepository.delete(inventory);
            } else {
                log.info("Updated inventory quantity after removal, inventoryId={}, newQuantity={}",
                      id, inventory.getQuantity());
                inventoryRepository.save(inventory);
            }
        }, () -> {
            throw new RuntimeException("Item not found in inventory"); // TODO: Custom exception if applicable
        });
    }
}
