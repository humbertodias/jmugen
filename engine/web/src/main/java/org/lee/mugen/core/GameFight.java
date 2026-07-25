package org.lee.mugen.core;

import org.lee.mugen.sprite.character.Sprite;

/** GWT stub: minimal fight engine for title/menu compile. */
public class GameFight {

    private static final GameFight INSTANCE = new GameFight();
    private final GameGlobalEvents globalEvents = new GameGlobalEvents();

    public static GameFight getInstance() {
        return INSTANCE;
    }

    public static void clear() {
    }

    public GameGlobalEvents getGlobalEvents() {
        return globalEvents;
    }

    public Sprite getSpriteInstance(String spriteId) {
        return new Sprite(spriteId);
    }

    public int countHelper(String spriteId) {
        return 0;
    }
}
