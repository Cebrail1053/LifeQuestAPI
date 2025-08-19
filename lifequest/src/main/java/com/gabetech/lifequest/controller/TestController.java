package com.gabetech.lifequest.controller;


import com.gabetech.lifequest.model.dto.PlayerResponseDTO;
import com.gabetech.lifequest.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final PlayerService playerService;
    
    //TODO: Find out why child entities are not being fetched
    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponseDTO> testEndpoint(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @GetMapping
    public ResponseEntity<List<PlayerResponseDTO>> testEndpoint() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }
}
