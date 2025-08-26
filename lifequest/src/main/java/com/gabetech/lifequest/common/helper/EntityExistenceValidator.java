package com.gabetech.lifequest.common.helper;

import com.gabetech.lifequest.model.entity.Achievement;
import com.gabetech.lifequest.model.entity.Item;
import com.gabetech.lifequest.model.entity.Player;
import com.gabetech.lifequest.model.entity.Quest;
import com.gabetech.lifequest.repository.AchievementRepository;
import com.gabetech.lifequest.repository.ItemRepository;
import com.gabetech.lifequest.repository.PlayerRepository;
import com.gabetech.lifequest.repository.QuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityExistenceValidator {

    private final PlayerRepository playerRepository;
    private final AchievementRepository achievementRepository;
    private final QuestRepository questRepository;
    private final ItemRepository itemRepository;

    public boolean validateExists(Long id, Class<?> entityClass) {
        if (entityClass.equals(Player.class)) {
            return playerRepository.existsById(id);
        } else if (entityClass.equals(Achievement.class)) {
            return achievementRepository.existsById(id);
        } else if (entityClass.equals(Quest.class)) {
            return questRepository.existsById(id);
        } else if (entityClass.equals(Item.class)) {
            return itemRepository.existsById(id);
        } else {
            throw new IllegalArgumentException("Unsupported entity class: " + entityClass.getName());
        }
    }
}
