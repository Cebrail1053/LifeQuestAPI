package com.gabetech.lifequest.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabetech.lifequest.model.entity.embed.PlayerAchievementId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "player_achievement")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PlayerAchievement {

    @EmbeddedId
    @JsonIgnore
    private PlayerAchievementId id;

    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name = "player_id")
    @JsonBackReference("player-achievements")
    private Player player;

    @ManyToOne
    @MapsId("achievementId")
    @JoinColumn(name = "achievement_id")
    private Achievement achievement;

    @Column(name = "unlocked_at")
    private LocalDateTime unlockedAt;
}
