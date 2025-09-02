package com.gabetech.lifequest.common;

public class Path {

    public static final String API_V1 = "/api/v1";

    public static final String PLAYERS = "/players";
    public static final String PLAYER_BY_ID = PLAYERS + "/{id}";

    public static final String QUESTS = "/quests";
    public static final String QUEST_BY_ID = QUESTS + "/{id}";

    public static final String ACHIEVEMENTS = "/achievements";

    public static final String ITEMS = "/items";

    public static final String PLAYER_QUEST_BY_ID = PLAYER_BY_ID + QUEST_BY_ID;
    public static final String PLAYER_INVENTORY = PLAYER_BY_ID + ITEMS + "/{id}";
    public static final String PLAYER_ACHIEVEMENTS = PLAYER_BY_ID + ACHIEVEMENTS;
}
