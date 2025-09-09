package com.gabetech.lifequest.controller;

import com.gabetech.lifequest.common.Path;
import com.gabetech.lifequest.domain.dto.ItemRequestDTO;
import com.gabetech.lifequest.domain.dto.ItemResponseDTO;
import com.gabetech.lifequest.service.ItemService;
import lombok.AllArgsConstructor;
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
public class ItemController {

    private final ItemService itemService;

    @PostMapping(Path.ITEMS)
    public ResponseEntity<ItemResponseDTO> createItem(@RequestBody ItemRequestDTO requestDTO) {
        ItemResponseDTO responseDTO = itemService.createItem(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(Path.ITEMS)
    public ResponseEntity<List<ItemResponseDTO>> getAllItems(@RequestParam(required = false) String rarity,
                                                             @RequestParam(required = false) String itemType) {
        List<ItemResponseDTO> items = itemService.getAllItems(rarity, itemType);
        return ResponseEntity.ok(items);
    }
}
