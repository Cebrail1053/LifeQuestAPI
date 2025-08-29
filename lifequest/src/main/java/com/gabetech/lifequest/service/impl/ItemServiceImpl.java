package com.gabetech.lifequest.service.impl;

import com.gabetech.lifequest.common.utils.MapperUtil;
import com.gabetech.lifequest.model.dto.ItemRequestDTO;
import com.gabetech.lifequest.model.dto.ItemResponseDTO;
import com.gabetech.lifequest.model.entity.Item;
import com.gabetech.lifequest.model.enums.ItemType;
import com.gabetech.lifequest.model.enums.Rarity;
import com.gabetech.lifequest.repository.ItemRepository;
import com.gabetech.lifequest.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public List<ItemResponseDTO> getAllItems(String rarityFilter, String itemTypeFilter) {
        final Rarity rarity = parseEnum(Rarity.class, rarityFilter, "Invalid rarity filter");
        final ItemType itemType = parseEnum(ItemType.class, itemTypeFilter, "Invalid item type filter");

        Stream<Item> itemStream = itemRepository.findAll().stream();
        if (rarity != null) {
            itemStream = itemStream.filter(item -> item.getRarity().equals(rarity));
        }
        if (itemType != null) {
            itemStream = itemStream.filter(item -> item.getType().equals(itemType));
        }
        return itemStream.map(MapperUtil::toDto).toList();
    }

    private <E extends Enum<E>> E parseEnum(Class<E> enumClass, String value, String errorMessage) {
        if (StringUtils.hasText(value)) {
            try {
                return Enum.valueOf(enumClass, value.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(errorMessage + ": " + value);
            }
        }
        return null;
    }

    @Override
    public ItemResponseDTO createItem(ItemRequestDTO requestDTO) {
        Item quest = MapperUtil.toEntity(requestDTO);
        return MapperUtil.toDto(itemRepository.save(quest));
    }
}
