package org.lee.mugen.sprite.character;

import org.lee.mugen.geom.MugenPoint;
import org.lee.mugen.sprite.entity.PointF;

/** GWT compile stub. */
public class SpriteCns {

    public enum MoveType {
        I, A, H, N
    }

    public enum Type {
        Player, Helper, Unknown
    }

    private int life = 1000;
    private final PointF velset = new PointF();
    private final MugenPoint lastHitdef = new MugenPoint();
    private int playerpush;

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public PointF getVelset() {
        return velset;
    }

    public MugenPoint getLastHitdef() {
        return lastHitdef;
    }

    public void setPlayerpush(int playerpush) {
        this.playerpush = playerpush;
    }

    public int getPlayerpush() {
        return playerpush;
    }

    public MoveType getMovetype() {
        return MoveType.N;
    }

    public Type getType() {
        return Type.Unknown;
    }
}
