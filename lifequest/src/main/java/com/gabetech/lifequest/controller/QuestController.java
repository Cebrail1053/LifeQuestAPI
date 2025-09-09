package com.gabetech.lifequest.controller;

import com.gabetech.lifequest.common.Path;
import com.gabetech.lifequest.domain.dto.QuestRequestDTO;
import com.gabetech.lifequest.domain.dto.QuestResponseDTO;
import com.gabetech.lifequest.service.QuestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class QuestController {

    private final QuestService questService;

    @PostMapping(Path.QUESTS)
    public ResponseEntity<QuestResponseDTO> createQuest(@RequestBody QuestRequestDTO requestDTO) {
        log.info("event=\"Create Quest\" status=\"initiated\" request={}", requestDTO);
        QuestResponseDTO responseDTO = questService.createQuest(requestDTO);
        log.info("event=\"Create Quest\" status=\"completed\" questId={}", responseDTO.id());
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(Path.QUESTS)
    public ResponseEntity<List<QuestResponseDTO>> getAllQuests(@RequestParam(required = false) String difficulty) {
        log.info("event=\"Get All Quests\" status=\"initiated\" difficulty={}", difficulty);
        List<QuestResponseDTO> quests = questService.getAllQuests(difficulty);
        log.info("event=\"Get All Quests\" status=\"completed\" count={}", quests.size());
        return ResponseEntity.ok(quests);
    }

    @GetMapping(Path.QUEST_BY_ID)
    public ResponseEntity<QuestResponseDTO> getQuestById(@PathVariable Long questId) {
        log.info("event=\"Get Quest By ID\" status=\"initiated\" questId={}", questId);
        QuestResponseDTO responseDTO = questService.getQuestById(questId);
        log.info("event=\"Get Quest By ID\" status=\"completed\" questId={}", questId);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping(Path.QUEST_BY_ID)
    public ResponseEntity<QuestResponseDTO> updateQuest(@PathVariable Long questId,
                                                        @RequestBody QuestRequestDTO requestDTO) {
        log.info("event=\"Update Quest\" status=\"initiated\" questId={} request={}", questId, requestDTO);
        QuestResponseDTO responseDTO = questService.updateQuest(questId, requestDTO);
        log.info("event=\"Update Quest\" status=\"completed\" questId={}", questId);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping(Path.QUEST_BY_ID)
    public ResponseEntity<?> deleteQuest(@PathVariable Long questId) {
        log.info("event=\"Delete Quest\" status=\"initiated\" questId={}", questId);
        questService.deleteQuest(questId);
        log.info("event=\"Delete Quest\" status=\"completed\" questId={}", questId);
        return ResponseEntity.ok().body("Quest deleted successfully");
    }
}
