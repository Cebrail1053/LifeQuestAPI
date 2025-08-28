-- ===============================
-- Create Schema
-- ===============================
CREATE SCHEMA IF NOT EXISTS lifequestdb;

-- ===============================
-- Set the schema to be used
-- ===============================
SET SCHEMA lifequestdb;

-- ===============================
-- Players
-- ===============================
CREATE TABLE IF NOT EXISTS player (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(75) NOT NULL,
    level INT NOT NULL DEFAULT 1,
    xp INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===============================
-- Quests
-- ===============================
CREATE TABLE IF NOT EXISTS quest (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    difficulty VARCHAR(10) NOT NULL, -- EASY, MEDIUM, HARD
    xp_reward INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===============================
-- Items
-- ===============================
CREATE TABLE IF NOT EXISTS item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(20) NOT NULL, -- WEAPON, ARMOR, POTION, ARTIFACT
    rarity VARCHAR(15) NOT NULL, -- COMMON, RARE, EPIC, LEGENDARY
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===============================
-- Achievements
-- ===============================
CREATE TABLE IF NOT EXISTS achievement (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL,
    condition VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===============================
-- Player_Quest (junction table)
-- ===============================
CREATE TABLE IF NOT EXISTS player_quest (
    player_id INT NOT NULL,
    quest_id INT NOT NULL,
    status VARCHAR(15) NOT NULL, -- ASSIGNED, COMPLETED
    completed_at TIMESTAMP,
    PRIMARY KEY (player_id, quest_id),
    FOREIGN KEY (player_id) REFERENCES player(id) ON DELETE CASCADE,
    FOREIGN KEY (quest_id) REFERENCES quest(id) ON DELETE CASCADE
);

-- ===============================
-- Inventory (junction table)
-- ===============================
CREATE TABLE IF NOT EXISTS inventory (
    player_id INT NOT NULL,
    item_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 0,
    PRIMARY KEY (player_id, item_id),
    FOREIGN KEY (player_id) REFERENCES player(id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES item(id) ON DELETE CASCADE
);

-- ===============================
-- Player_Achievement (junction table)
-- ===============================
CREATE TABLE IF NOT EXISTS player_achievement (
    player_id INT NOT NULL,
    achievement_id INT NOT NULL,
    unlocked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (player_id, achievement_id),
    FOREIGN KEY (player_id) REFERENCES player(id) ON DELETE CASCADE,
    FOREIGN KEY (achievement_id) REFERENCES achievement(id) ON DELETE CASCADE
);
