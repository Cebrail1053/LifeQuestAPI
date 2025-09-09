package com.gabetech.lifequest.controller;

import com.gabetech.lifequest.common.Path;
import com.gabetech.lifequest.common.utils.MapperUtil;
import com.gabetech.lifequest.domain.dto.ItemResponseDTO;
import com.gabetech.lifequest.domain.dto.PlayerRequestDTO;
import com.gabetech.lifequest.domain.dto.PlayerResponseDTO;
import com.gabetech.lifequest.model.entity.Achievement;
import com.gabetech.lifequest.model.entity.Inventory;
import com.gabetech.lifequest.model.entity.Item;
import com.gabetech.lifequest.model.entity.PlayerAchievement;
import com.gabetech.lifequest.service.InventoryService;
import com.gabetech.lifequest.service.PlayerQuestService;
import com.gabetech.lifequest.service.PlayerService;
import lombok.AllArgsConstructor;
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
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping(Path.API_V1)
@AllArgsConstructor
public class PlayerController {

    private final PlayerService playerService;
    private final InventoryService inventoryService;
    private final PlayerQuestService playerQuestService;

    @PostMapping(Path.PLAYERS)
    public ResponseEntity<PlayerResponseDTO> createPlayer(@RequestBody PlayerRequestDTO requestDTO) {
        PlayerResponseDTO responseDTO = playerService.createPlayer(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping(Path.PLAYERS)
    public ResponseEntity<List<PlayerResponseDTO>> getAllPlayers() {
        List<PlayerResponseDTO> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    @GetMapping(Path.PLAYER_BY_ID)
    public ResponseEntity<PlayerResponseDTO> getPlayerById(@PathVariable Long playerId) {
        PlayerResponseDTO responseDTO = playerService.getPlayerById(playerId);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(Path.PLAYER_ACHIEVEMENTS)
    public ResponseEntity<List<Achievement>> getPlayerAchievementsById(@PathVariable Long playerId) {
        List<Achievement> responseDTO = playerService.getPlayerById(playerId).achievements()
              .stream()
              .map(PlayerAchievement::getAchievement)
              .toList();
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(Path.PLAYER_INVENTORY)
    public ResponseEntity<Set<Inventory>> getPlayerInventoryById(@PathVariable Long playerId) {
        Set<Inventory> responseDTO = playerService.getPlayerById(playerId).inventory();
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping(Path.PLAYER_BY_ID)
    public ResponseEntity<PlayerResponseDTO> updatePlayer(@PathVariable Long playerId,
                                                          @RequestBody PlayerRequestDTO requestDTO) {
        PlayerResponseDTO responseDTO = playerService.updatePlayer(playerId, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping(Path.PLAYER_BY_ID)
    public ResponseEntity<?> deletePlayer(@PathVariable Long playerId) {
        playerService.deletePlayer(playerId);
        return ResponseEntity.ok().body("Player deleted successfully");
    }

    @PostMapping(Path.PLAYER_QUEST_BY_ID)
    public ResponseEntity<?> assignQuestToPlayer(@PathVariable Long playerId, @PathVariable Long questId) {
        playerQuestService.assignQuestToPlayer(playerId, questId);
        return ResponseEntity.ok().body("Quest assigned to player successfully");
    }

    @PutMapping(Path.PLAYER_QUEST_BY_ID + "/complete")
    public ResponseEntity<?> completeQuestForPlayer(@PathVariable Long playerId, @PathVariable Long questId) {
        playerQuestService.completeQuestForPlayer(playerId, questId);
        return ResponseEntity.ok().body("Quest completed for player successfully");
    }

    @PostMapping(Path.PLAYER_ITEM_BY_ID)
    public ResponseEntity<?> addItemToPlayerInventory(@PathVariable Long playerId, @PathVariable Long itemId, @RequestParam int quantity) {
        inventoryService.addItemToInventory(playerId, itemId, quantity);
        return ResponseEntity.ok().body("Item added to player inventory successfully");
    }

    @DeleteMapping(Path.PLAYER_ITEM_BY_ID)
    public ResponseEntity<?> deleteItemFromPlayerInventory(@PathVariable Long playerId, @PathVariable Long itemId, @RequestParam int quantity) {
        inventoryService.removeItemFromInventory(playerId, itemId, quantity);
        return ResponseEntity.ok().body("Item deleted from player inventory successfully");
    }
}
