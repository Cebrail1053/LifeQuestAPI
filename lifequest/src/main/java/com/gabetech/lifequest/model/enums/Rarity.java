package com.gabetech.lifequest.model.enums;

public enum Rarity {

    COMMON("Common"),
    UNCOMMON("Uncommon"),
    RARE("Rare"),
    EPIC("Epic"),
    LEGENDARY("Legendary");

    private final String rarity;

    Rarity(String rarity) {
        this.rarity = rarity;
    }

    public String getRarity() {
        return rarity;
    }

    @Override
    public String toString() {
        return rarity;
    }
}
