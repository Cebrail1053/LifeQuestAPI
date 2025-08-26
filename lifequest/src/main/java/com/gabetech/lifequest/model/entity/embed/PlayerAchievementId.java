package com.gabetech.lifequest.model.entity.embed;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
public class PlayerAchievementId implements Serializable {

    private int playerId;
    private int achievementId;

}
