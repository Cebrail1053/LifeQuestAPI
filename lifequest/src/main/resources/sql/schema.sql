-- ===============================
-- Players
-- ===============================
CREATE TABLE player (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    level INT NOT NULL DEFAULT 1,
    xp INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===============================
-- Quests
-- ===============================
CREATE TABLE quest (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    difficulty VARCHAR(10) NOT NULL, -- EASY, MEDIUM, HARD
    xp_reward INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===============================
-- Items
-- ===============================
CREATE TABLE item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(20) NOT NULL, -- WEAPON, ARMOR, POTION, ARTIFACT
    rarity VARCHAR(15) NOT NULL, -- COMMON, RARE, EPIC, LEGENDARY
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===============================
-- Achievements
-- ===============================
CREATE TABLE achievement (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    condition VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===============================
-- Player_Quest (junction table)
-- ===============================
CREATE TABLE player_quest (
    player_id BIGINT NOT NULL,
    quest_id BIGINT NOT NULL,
    status VARCHAR(15) NOT NULL, -- ASSIGNED, COMPLETED
    completed_at TIMESTAMP,
    PRIMARY KEY (player_id, quest_id),
    FOREIGN KEY (player_id) REFERENCES player(id) ON DELETE CASCADE,
    FOREIGN KEY (quest_id) REFERENCES quest(id) ON DELETE CASCADE
);

-- ===============================
-- Inventory (junction table)
-- ===============================
CREATE TABLE inventory (
    player_id BIGINT NOT NULL,
    item_id BIGINT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    PRIMARY KEY (player_id, item_id),
    FOREIGN KEY (player_id) REFERENCES player(id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES item(id) ON DELETE CASCADE
);

-- ===============================
-- Player_Achievement (junction table)
-- ===============================
CREATE TABLE player_achievement (
    player_id BIGINT NOT NULL,
    achievement_id BIGINT NOT NULL,
    unlocked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (player_id, achievement_id),
    FOREIGN KEY (player_id) REFERENCES player(id) ON DELETE CASCADE,
    FOREIGN KEY (achievement_id) REFERENCES achievement(id) ON DELETE CASCADE
);
