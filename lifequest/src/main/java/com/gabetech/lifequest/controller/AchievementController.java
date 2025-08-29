package com.gabetech.lifequest.controller;

import com.gabetech.lifequest.common.Path;
import com.gabetech.lifequest.model.dto.AchievementRequestDTO;
import com.gabetech.lifequest.model.dto.AchievementResponseDTO;
import com.gabetech.lifequest.service.AchievementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Path.API_V1)
@AllArgsConstructor
public class AchievementController {

    private final AchievementService achievementService;

    @PostMapping(Path.ACHIEVEMENTS)
    public ResponseEntity<AchievementResponseDTO> createAchievement(@RequestBody AchievementRequestDTO requestDTO) {
        AchievementResponseDTO responseDTO = achievementService.createAchievement(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(Path.ACHIEVEMENTS)
    public ResponseEntity<List<AchievementResponseDTO>> getAllAchievements() {
        List<AchievementResponseDTO> achievements = achievementService.getAllAchievements();
        return ResponseEntity.ok(achievements);
    }
}
