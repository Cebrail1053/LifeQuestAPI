package com.gabetech.lifequest.service;

import com.gabetech.lifequest.model.entity.Item;

import java.util.List;

public interface ItemService {

    List<Item> getAllItems();

    Item createItem(Item item);
}
