package com.gabetech.lifequest.model.enums;

public enum ItemType {
    WEAPON("Weapon"),
    ARMOR("Armor"),
    POTION("Potion"),
    ARTIFACT("Artifact");

    private final String itemType;

    ItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemType() {
        return itemType;
    }

    @Override
    public String toString() {
        return itemType;
    }
}
