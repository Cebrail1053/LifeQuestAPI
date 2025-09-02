package com.gabetech.lifequest.controller;

import com.gabetech.lifequest.common.Path;
import com.gabetech.lifequest.domain.dto.QuestRequestDTO;
import com.gabetech.lifequest.domain.dto.QuestResponseDTO;
import com.gabetech.lifequest.service.QuestService;
import lombok.AllArgsConstructor;
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

@RestController
@RequestMapping(Path.API_V1)
@AllArgsConstructor
public class QuestController {

    private final QuestService questService;

    @PostMapping(Path.QUESTS)
    public ResponseEntity<QuestResponseDTO> createQuest(@RequestBody QuestRequestDTO requestDTO) {
        QuestResponseDTO responseDTO = questService.createQuest(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(Path.QUESTS)
    public ResponseEntity<List<QuestResponseDTO>> getAllQuests(@RequestParam(required = false) String difficulty) {
        List<QuestResponseDTO> quests = questService.getAllQuests(difficulty);
        return ResponseEntity.ok(quests);
    }

    @GetMapping(Path.QUEST_BY_ID)
    public ResponseEntity<QuestResponseDTO> getQuestById(@PathVariable Long id) {
        QuestResponseDTO responseDTO = questService.getQuestById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping(Path.QUEST_BY_ID)
    public ResponseEntity<QuestResponseDTO> updateQuest(@PathVariable Long id,
                                                        @RequestBody QuestRequestDTO requestDTO) {
        QuestResponseDTO responseDTO = questService.updateQuest(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping(Path.QUEST_BY_ID)
    public ResponseEntity<?> deleteQuest(@PathVariable Long id) {
        questService.deleteQuest(id);
        return ResponseEntity.ok().body("Quest deleted successfully");
    }
}
