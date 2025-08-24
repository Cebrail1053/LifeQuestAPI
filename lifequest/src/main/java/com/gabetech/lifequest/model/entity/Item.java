package com.gabetech.lifequest.model.entity;

import com.gabetech.lifequest.model.enums.ItemType;
import com.gabetech.lifequest.model.enums.Rarity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private ItemType type;
    @Enumerated(value = EnumType.STRING)
    private Rarity rarity;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
