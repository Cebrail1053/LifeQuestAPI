package com.gabetech.lifequest.model.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Inventory {

    private int playerId;
    private int itemId;
    private int quantity;
}
