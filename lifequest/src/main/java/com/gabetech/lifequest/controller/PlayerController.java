package com.gabetech.lifequest.controller;

import com.gabetech.lifequest.common.Path;
import com.gabetech.lifequest.domain.dto.PlayerRequestDTO;
import com.gabetech.lifequest.domain.dto.PlayerResponseDTO;
import com.gabetech.lifequest.model.entity.Achievement;
import com.gabetech.lifequest.model.entity.Inventory;
import com.gabetech.lifequest.model.entity.PlayerAchievement;
import com.gabetech.lifequest.service.InventoryService;
import com.gabetech.lifequest.service.PlayerQuestService;
import com.gabetech.lifequest.service.PlayerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(Path.API_V1)
@AllArgsConstructor
@Slf4j
public class PlayerController {

    private final PlayerService playerService;
    private final InventoryService inventoryService;
    private final PlayerQuestService playerQuestService;

    @PostMapping(Path.PLAYERS)
    public ResponseEntity<PlayerResponseDTO> createPlayer(@RequestBody PlayerRequestDTO requestDTO) {
        log.info("event=\"Create Player\" status=\"initiated\" request={}", requestDTO);
        PlayerResponseDTO responseDTO = playerService.createPlayer(requestDTO);
        log.info("event=\"Create Player\" status=\"completed\" PlayerId={}", responseDTO.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping(Path.PLAYERS)
    public ResponseEntity<List<PlayerResponseDTO>> getAllPlayers() {
        log.info("event=\"Get All Players\" status=\"initiated\"");
        List<PlayerResponseDTO> players = playerService.getAllPlayers();
        log.info("event=\"Get All Players\" status=\"completed\" count={}", players.size());
        return ResponseEntity.ok(players);
    }

    @GetMapping(Path.PLAYER_BY_ID)
    public ResponseEntity<PlayerResponseDTO> getPlayerById(@PathVariable Long playerId) {
        log.info("event=\"Get Player By ID\" status=\"initiated\" playerId={}", playerId);
        PlayerResponseDTO responseDTO = playerService.getPlayerById(playerId);
        log.info("event=\"Get Player By ID\" status=\"completed\" playerId={}", playerId);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(Path.PLAYER_ACHIEVEMENTS)
    public ResponseEntity<List<Achievement>> getPlayerAchievementsById(@PathVariable Long playerId) {
        log.info("event=\"Get Player Achievements By ID\" status=\"initiated\" playerId={}", playerId);
        List<Achievement> responseDTO = playerService.getPlayerById(playerId).achievements()
              .stream()
              .map(PlayerAchievement::getAchievement)
              .toList();
        log.info("event=\"Get Player Achievements By ID\" status=\"completed\" playerId={}", playerId);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(Path.PLAYER_INVENTORY)
    public ResponseEntity<Set<Inventory>> getPlayerInventoryById(@PathVariable Long playerId) {
        log.info("event=\"Get Player Inventory By ID\" status=\"initiated\" playerId={}", playerId);
        Set<Inventory> responseDTO = playerService.getPlayerById(playerId).inventory();
        log.info("event=\"Get Player Inventory By ID\" status=\"completed\" playerId={}", playerId);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping(Path.PLAYER_BY_ID)
    public ResponseEntity<PlayerResponseDTO> updatePlayer(@PathVariable Long playerId,
                                                          @RequestBody PlayerRequestDTO requestDTO) {
        log.info("event=\"Update Player\" status=\"initiated\" playerId={} request={}", playerId, requestDTO);
        PlayerResponseDTO responseDTO = playerService.updatePlayer(playerId, requestDTO);
        log.info("event=\"Update Player\" status=\"completed\" playerId={}", playerId);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping(Path.PLAYER_BY_ID)
    public ResponseEntity<?> deletePlayer(@PathVariable Long playerId) {
        log.info("event=\"Delete Player\" status=\"initiated\" playerId={}", playerId);
        playerService.deletePlayer(playerId);
        log.info("event=\"Delete Player\" status=\"completed\" playerId={}", playerId);
        return ResponseEntity.ok().body("Player deleted successfully");
    }

    @PostMapping(Path.PLAYER_QUEST_BY_ID)
    public ResponseEntity<?> assignQuestToPlayer(@PathVariable Long playerId, @PathVariable Long questId) {
        log.info("event=\"Assign Quest To Player\" status=\"initiated\" playerId={} questId={}", playerId, questId);
        playerQuestService.assignQuestToPlayer(playerId, questId);
        log.info("event=\"Assign Quest To Player\" status=\"completed\" playerId={} questId={}", playerId, questId);
        return ResponseEntity.ok().body("Quest assigned to player successfully");
    }

    @PutMapping(Path.PLAYER_QUEST_BY_ID + "/complete")
    public ResponseEntity<?> completeQuestForPlayer(@PathVariable Long playerId, @PathVariable Long questId) {
        log.info("event=\"Complete Quest For Player\" status=\"initiated\" playerId={} questId={}", playerId, questId);
        playerQuestService.completeQuestForPlayer(playerId, questId);
        log.info("event=\"Complete Quest For Player\" status=\"completed\" playerId={} questId={}", playerId, questId);
        return ResponseEntity.ok().body("Quest completed for player successfully");
    }

    @PostMapping(Path.PLAYER_ITEM_BY_ID)
    public ResponseEntity<?> addItemToPlayerInventory(@PathVariable Long playerId,
                                                      @PathVariable Long itemId, @RequestParam int quantity) {
        log.info("event=\"Add Item To Player Inventory\" status=\"initiated\" playerId={} itemId={} quantity={}", playerId, itemId, quantity);
        inventoryService.addItemToInventory(playerId, itemId, quantity);
        log.info("event=\"Add Item To Player Inventory\" status=\"completed\" playerId={} itemId={} quantity={}", playerId, itemId, quantity);
        return ResponseEntity.ok().body("Item added to player inventory successfully");
    }

    @DeleteMapping(Path.PLAYER_ITEM_BY_ID)
    public ResponseEntity<?> deleteItemFromPlayerInventory(@PathVariable Long playerId,
                                                           @PathVariable Long itemId,
                                                           @RequestParam int quantity) {
        log.info("event=\"Delete Item From Player Inventory\" status=\"initiated\" playerId={} itemId={} quantity={}", playerId, itemId, quantity);
        inventoryService.removeItemFromInventory(playerId, itemId, quantity);
        log.info("event=\"Delete Item From Player Inventory\" status=\"completed\" playerId={} itemId={} quantity={}", playerId, itemId, quantity);
        return ResponseEntity.ok().body("Item deleted from player inventory successfully");
    }
}
