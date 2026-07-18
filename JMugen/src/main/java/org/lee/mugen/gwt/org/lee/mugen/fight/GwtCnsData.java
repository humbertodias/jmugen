package org.lee.mugen.fight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parsed subset of a character {@code *.cns} for the GWT fight stub (Phase 1 hybrid CNS).
 *
 * <p>Holds constants, HitDefs, and StateCtrls for the hybrid CNS runtime (Phase 2).
 */
public final class GwtCnsData {

    /** One HitDef from a [State …] controller. */
    public static final class HitDef {
        public int damage;
        public int guardDamage;
        public int pauseP1 = 8;
        public int pauseP2 = 8;
        public float groundVelocityX = -4f;
        public float airVelocityX = -1.5f;
        public float airVelocityY = -3f;
        public int groundSlideTime = 8;
        public int groundHitTime = 10;
        public int animElem; // 0 = any / Time=0 style
        public boolean fall;
        public boolean trip;
        public String guardFlag = "MA";
        public String hitFlag = "MAF";
        public int hitsoundGrp = 5;
        public int hitsoundNum = 0;
        public int guardsoundGrp = 6;
        public int guardsoundNum = 0;
        /** When true, sound is from character .snd (deferred on GWT). */
        public boolean hitsoundFromSprite;
        public boolean guardsoundFromSprite;
        /**
         * HitDef {@code animtype}: 0=Light, 1=Medium, 2=Hard (common1 5000+animtype / 5005+animtype).
         */
        public int animType;
    }

    /** One [State …] controller (ChangeState, VelSet, HitDef, …). */
    public static final class StateCtrl {
        public String type = "";
        public final List<String> triggerAll = new ArrayList<String>();
        /** Each inner list is an OR group (trigger1 / trigger2 / …); all must hold within a group. */
        public final List<List<String>> triggerGroups = new ArrayList<List<String>>();
        public final Map<String, String> params = new HashMap<String, String>();
        public boolean ignoreHitPause;
        /** 1 = every frame (default); 0 = fire once. */
        public int persistent = 1;
    }

    /** Lightweight [Statedef] header + controllers. */
    public static final class StateMeta {
        public int id;
        public String type = "S";
        public String moveType = "I";
        public String physics = "S";
        public int anim = -1;
        public int ctrl = -1;
        public float velsetX;
        public float velsetY;
        public boolean hasVelset;
        public final List<HitDef> hitDefs = new ArrayList<HitDef>();
        public final List<StateCtrl> controllers = new ArrayList<StateCtrl>();
    }

    public int life = 1000;
    public int attack = 100;
    public int defence = 100;
    public int airJuggle = 15;
    public int lieDownTime = 60;

    public float xScale = 1f;
    public float yScale = 1f;
    public int groundBack = 15;
    public int groundFront = 16;
    public int height = 60;

    public float walkFwd = 2.4f;
    public float walkBack = -2.2f;
    public float runFwdX = 4.6f;
    public float runBackX = -4.5f;
    public float runBackY = -3.8f;
    public float jumpNeuX = 0f;
    public float jumpNeuY = -8.4f;
    public float jumpBackX = -2.55f;
    public float jumpFwdX = 2.5f;
    public float yAccel = 0.44f;
    public float standFriction = 0.85f;
    public float crouchFriction = 0.82f;

    public final Map<Integer, StateMeta> states = new HashMap<Integer, StateMeta>();

    public HitDef getHitDef(int stateId, int hitIndex) {
        StateMeta meta = states.get(stateId);
        if (meta == null || meta.hitDefs.isEmpty()) {
            return null;
        }
        if (hitIndex < 0) {
            hitIndex = 0;
        }
        if (hitIndex >= meta.hitDefs.size()) {
            return meta.hitDefs.get(meta.hitDefs.size() - 1);
        }
        return meta.hitDefs.get(hitIndex);
    }

    public int getHitDefCount(int stateId) {
        StateMeta meta = states.get(stateId);
        return meta == null ? 0 : meta.hitDefs.size();
    }

    public StateMeta getState(int stateId) {
        return states.get(stateId);
    }
}
