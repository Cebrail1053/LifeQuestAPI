package com.gabetech.lifequest.service;

import com.gabetech.lifequest.model.dto.ItemRequestDTO;
import com.gabetech.lifequest.model.dto.ItemResponseDTO;

import java.util.List;

public interface ItemService {

    List<ItemResponseDTO> getAllItems();

    ItemResponseDTO createItem(ItemRequestDTO requestDTO);
}
