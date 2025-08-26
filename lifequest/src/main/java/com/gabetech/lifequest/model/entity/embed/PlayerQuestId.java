package com.gabetech.lifequest.model.entity.embed;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
public class PlayerQuestId implements Serializable {

    private int playerId;
    private int questId;

}
