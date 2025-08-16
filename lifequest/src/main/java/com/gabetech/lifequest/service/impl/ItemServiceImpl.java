package com.gabetech.lifequest.service.impl;

import com.gabetech.lifequest.model.entity.Item;
import com.gabetech.lifequest.repository.ItemRepository;
import com.gabetech.lifequest.service.ItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    @Transactional
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }
}
