package org.lee.mugen.sprite.character;

/** GWT compile stub for expression evaluation and compile-time references. */
public class Sprite {

    private final String spriteId;
    private final SpriteCns info = new SpriteCns();
    private final SpriteState spriteState = new SpriteState();
    private final SpriteAnimManager animManager = new SpriteAnimManager("web");

    public Sprite() {
        this("web");
    }

    public Sprite(String spriteId) {
        this.spriteId = spriteId;
    }

    public String getSpriteId() {
        return spriteId;
    }

    public SpriteCns getInfo() {
        return info;
    }

    public SpriteState getSpriteState() {
        return spriteState;
    }

    public SpriteAnimManager getSprAnimMng() {
        return animManager;
    }

    public int getPal() {
        return 1;
    }

    public boolean isFlip() {
        return false;
    }

    public boolean isDebugRender() {
        return false;
    }
}
