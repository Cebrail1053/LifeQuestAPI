package com.gabetech.lifequest.controller;

import com.gabetech.lifequest.common.Path;
import com.gabetech.lifequest.model.dto.PlayerRequestDTO;
import com.gabetech.lifequest.model.dto.PlayerResponseDTO;
import com.gabetech.lifequest.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(Path.API_V1)
@AllArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping(Path.PLAYERS)
    public ResponseEntity<PlayerResponseDTO> createPlayer(@RequestBody PlayerRequestDTO requestDTO) {
        PlayerResponseDTO responseDTO = playerService.createPlayer(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(Path.PLAYERS)
    public ResponseEntity<List<PlayerResponseDTO>> getAllPlayers() {
        List<PlayerResponseDTO> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    @GetMapping(Path.PLAYER_BY_ID)
    public ResponseEntity<PlayerResponseDTO> getPlayerById(@PathVariable Long id) {
        PlayerResponseDTO responseDTO = playerService.getPlayerById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping(Path.PLAYER_BY_ID)
    public ResponseEntity<PlayerResponseDTO> updatePlayer(@PathVariable Long id,
                                                          @RequestBody PlayerRequestDTO requestDTO) {
        PlayerResponseDTO responseDTO = playerService.updatePlayer(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping(Path.PLAYER_BY_ID)
    public ResponseEntity<?> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.ok().body("Player deleted successfully");
    }

}
