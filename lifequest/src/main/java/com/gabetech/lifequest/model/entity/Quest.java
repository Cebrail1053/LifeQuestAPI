package com.gabetech.lifequest.model.entity;

import com.gabetech.lifequest.model.enums.Difficulty;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    private int xpReward;
    private LocalDateTime createdAt;
}
