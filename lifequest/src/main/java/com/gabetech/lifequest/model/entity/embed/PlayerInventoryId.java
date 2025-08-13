package com.gabetech.lifequest.model.entity.embed;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class PlayerInventoryId implements Serializable {

    private int playerId;
    private int itemId;

}
