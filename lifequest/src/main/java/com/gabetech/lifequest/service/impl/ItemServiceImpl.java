package com.gabetech.lifequest.service.impl;

import com.gabetech.lifequest.common.utils.MapperUtil;
import com.gabetech.lifequest.model.dto.ItemRequestDTO;
import com.gabetech.lifequest.model.dto.ItemResponseDTO;
import com.gabetech.lifequest.model.entity.Item;
import com.gabetech.lifequest.repository.ItemRepository;
import com.gabetech.lifequest.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public List<ItemResponseDTO> getAllItems() {
        return itemRepository.findAll().stream().map(MapperUtil::toDto).toList();
    }

    @Override
    public ItemResponseDTO createItem(ItemRequestDTO requestDTO) {
        Item quest = MapperUtil.toEntity(requestDTO);
        return MapperUtil.toDto(itemRepository.save(quest));
    }
}
