package com.gabetech.lifequest.model.entity.embed;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class PlayerQuestId implements Serializable {

    private int playerId;
    private int questId;

}
