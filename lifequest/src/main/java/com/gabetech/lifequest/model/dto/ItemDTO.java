package com.gabetech.lifequest.model.dto;

import com.gabetech.lifequest.model.enums.ItemType;
import com.gabetech.lifequest.model.enums.Rarity;

public record ItemDTO(int id, String name, ItemType type, Rarity rarity) {
}
