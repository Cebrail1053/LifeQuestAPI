package com.gabetech.lifequest.model.dto;

import com.gabetech.lifequest.model.entity.embed.PlayerInventoryId;

public record InventoryDTO(PlayerInventoryId id, int quantity) {
}
