package com.gabetech.lifequest.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabetech.lifequest.model.entity.embed.PlayerQuestId;
import com.gabetech.lifequest.model.enums.QuestStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "player_quest")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PlayerQuest {

    @EmbeddedId
    @JsonIgnore
    private PlayerQuestId id;

    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name = "player_id")
    @JsonBackReference("player-quests")
    private Player player;

    @ManyToOne
    @MapsId("questId")
    @JoinColumn(name = "quest_id")
    private Quest quest;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private QuestStatus status;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}
