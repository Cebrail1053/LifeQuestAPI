package com.gabetech.lifequest.service;

public interface InventoryService {

    void addItemToInventory(Long playerId, Long itemId, int quantity);

    void removeItemFromInventory(Long playerId, Long itemId, int quantity);
}
