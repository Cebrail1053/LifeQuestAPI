-- ===============================
-- Players
-- ===============================
INSERT INTO player (name, level, xp, created_at) VALUES
('Aria Stormblade', 5, 250, CURRENT_TIMESTAMP),
('Borin Ironfist', 3, 120, CURRENT_TIMESTAMP),
('Lyra Moonshadow', 1, 0, CURRENT_TIMESTAMP);

-- ===============================
-- Quests
-- ===============================
INSERT INTO quest (title, description, difficulty, xp_reward, created_at) VALUES
('Slay the Goblin King', 'Defeat the goblin king terrorizing the village.', 'HARD', 150, CURRENT_TIMESTAMP),
('Gather Healing Herbs', 'Collect 10 healing herbs from the Whispering Woods.', 'EASY', 50, CURRENT_TIMESTAMP),
('Escort the Merchant', 'Safely guide the merchant caravan to Rivertown.', 'MEDIUM', 100, CURRENT_TIMESTAMP);

-- ===============================
-- Items
-- ===============================
INSERT INTO item (name, type, rarity, created_at) VALUES
('Iron Sword', 'WEAPON', 'COMMON', CURRENT_TIMESTAMP),
('Elven Bow', 'WEAPON', 'RARE', CURRENT_TIMESTAMP),
('Health Potion', 'POTION', 'COMMON', CURRENT_TIMESTAMP),
('Dragonfire Amulet', 'ARTIFACT', 'EPIC', CURRENT_TIMESTAMP);

-- ===============================
-- Achievements
-- ===============================
INSERT INTO achievement (name, description, condition, created_at) VALUES
('First Blood', 'Complete your first quest', 'quests > 1', CURRENT_TIMESTAMP),
('Apprentice Adventurer', 'Reach level 5', 'level >= 5', CURRENT_TIMESTAMP),
('Treasure Hunter', 'Collect 10 rare or higher items', 'inventory.?[item.rarity.ordinal() >= T(Rarity).RARE.ordinal()].size() >= 10', CURRENT_TIMESTAMP);

-- ===============================
-- Player Quests (Assignments)
-- ===============================
INSERT INTO player_quest (player_id, quest_id, status, completed_at) VALUES
(1, 2, 'COMPLETED', CURRENT_TIMESTAMP),
(1, 3, 'ASSIGNED', NULL),
(2, 1, 'ASSIGNED', NULL);

-- ===============================
-- Inventory
-- ===============================
INSERT INTO inventory (player_id, item_id, quantity) VALUES
(1, 1, 1),
(1, 3, 5),
(2, 2, 1),
(3, 4, 1);

-- ===============================
-- Player Achievements
-- ===============================
INSERT INTO player_achievement (player_id, achievement_id, unlocked_at) VALUES
(1, 1, CURRENT_TIMESTAMP),
(1, 2, CURRENT_TIMESTAMP);
