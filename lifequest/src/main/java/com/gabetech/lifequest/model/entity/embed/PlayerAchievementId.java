package com.gabetech.lifequest.model.entity.embed;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class PlayerAchievementId implements Serializable {

    private int playerId;
    private int achievementId;

}
