package com.gabetech.lifequest.controller;

import com.gabetech.lifequest.common.Path;
import com.gabetech.lifequest.domain.dto.ItemRequestDTO;
import com.gabetech.lifequest.domain.dto.ItemResponseDTO;
import com.gabetech.lifequest.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Path.API_V1)
@AllArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @PostMapping(Path.ITEMS)
    public ResponseEntity<ItemResponseDTO> createItem(@RequestBody ItemRequestDTO requestDTO) {
        log.info("event=\"Create Item\" status=\"initiated\" request={}", requestDTO);
        ItemResponseDTO responseDTO = itemService.createItem(requestDTO);
        log.info("event=\"Create Item\" status=\"completed\" itemId={}", responseDTO.id());
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(Path.ITEMS)
    public ResponseEntity<List<ItemResponseDTO>> getAllItems(@RequestParam(required = false) String rarity,
                                                             @RequestParam(required = false) String itemType) {
        log.info("event=\"Get All Items\" status=\"initiated\" rarity={} itemType={}", rarity, itemType);
        List<ItemResponseDTO> items = itemService.getAllItems(rarity, itemType);
        log.info("event=\"Get All Items\" status=\"completed\" count={}", items.size());
        return ResponseEntity.ok(items);
    }
}
