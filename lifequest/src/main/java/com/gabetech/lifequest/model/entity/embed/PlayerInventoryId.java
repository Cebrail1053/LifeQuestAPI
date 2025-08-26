package com.gabetech.lifequest.model.entity.embed;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
public class PlayerInventoryId implements Serializable {

    private int playerId;
    private int itemId;

}
