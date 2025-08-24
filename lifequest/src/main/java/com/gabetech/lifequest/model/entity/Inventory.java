package com.gabetech.lifequest.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabetech.lifequest.model.entity.embed.PlayerInventoryId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Inventory {

    @EmbeddedId
    @JsonIgnore
    private PlayerInventoryId id;

    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name = "player_id")
    @JsonBackReference("player-inventory")
    private Player player;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;

    @Column
    private int quantity;
}
