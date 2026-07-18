package org.lee.mugen.fight;

import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.object.Rectangle;
import org.lee.mugen.parser.air.AirParser;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.sff.SffReader;
import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;
import org.lee.mugen.sprite.character.AnimElement;
import org.lee.mugen.sprite.character.SpriteAnimManager;
import org.lee.mugen.sprite.parser.Parser;

/** GWT: character animation + AIR hitboxes (clsn1/clsn2) for fight. */
public final class GwtFightFighterAnim {

    private final SpriteAnimManager anim;
    private final SpriteSFF sff;
    private int actionTicks;

    public GwtFightFighterAnim(String characterId, String sffFile, String airFile) throws Exception {
        String sffPath =
                Parser.getExistFile(JMugenConstant.RESOURCE + "chars/" + characterId + "/" + sffFile);
        sff = new SpriteSFF(new SffReader(sffPath, null), true, false);
        String airPath =
                Parser.getExistFile(JMugenConstant.RESOURCE + "chars/" + characterId + "/" + airFile);
        anim = new SpriteAnimManager(characterId, new AirParser(airPath));
        anim.setAction(0);
        actionTicks = estimateActionLength(0);
    }

    public void setAction(int action) {
        if (anim.getAction() != action) {
            anim.setAction(action);
            actionTicks = estimateActionLength(action);
        }
    }

    /** Force restart of an action (same or new), for CNS ChangeState / re-attacks. */
    public void restartAction(int action) {
        if (anim.getAction() != action) {
            anim.setAction(action);
        } else {
            anim.setAnimElem(0);
        }
        actionTicks = estimateActionLength(action);
    }

    public void process() {
        anim.process();
        if (actionTicks > 0) {
            actionTicks--;
        }
    }

    public boolean isActionFinished() {
        return actionTicks <= 0;
    }

    public ImageContainer getCurrentImage() {
        AnimElement elem = anim.getCurrentImageSprite();
        if (elem == null || elem.getAirData() == null) {
            return null;
        }
        int grp = elem.getAirData().getGrpNum();
        int img = elem.getAirData().getImgNum();
        if (grp < 0 || sff.getGroupSpr(grp) == null) {
            return null;
        }
        ImageSpriteSFF spr = sff.getGroupSpr(grp).getImgSpr(img);
        if (spr == null || spr.getImage() == null) {
            return null;
        }
        return (ImageContainer) spr.getImage();
    }

    public ImageSpriteSFF getCurrentImageSpriteSFF() {
        AnimElement elem = anim.getCurrentImageSprite();
        if (elem == null || elem.getAirData() == null) {
            return null;
        }
        int grp = elem.getAirData().getGrpNum();
        int img = elem.getAirData().getImgNum();
        if (grp < 0 || sff.getGroupSpr(grp) == null) {
            return null;
        }
        return sff.getGroupSpr(grp).getImgSpr(img);
    }

    public boolean isMirrorH() {
        AnimElement elem = anim.getCurrentImageSprite();
        return elem != null && elem.isMirrorH();
    }

    public boolean isMirrorV() {
        AnimElement elem = anim.getCurrentImageSprite();
        return elem != null && elem.isMirrorV();
    }

    public float getXOffset() {
        AnimElement elem = anim.getCurrentImageSprite();
        return elem != null ? elem.getXOffSet() : 0f;
    }

    public float getYOffset() {
        AnimElement elem = anim.getCurrentImageSprite();
        return elem != null ? elem.getYOffSet() : 0f;
    }

    public Rectangle[] getAttackBoxes() {
        AnimElement elem = anim.getCurrentImageSprite();
        return elem != null ? elem.getAtacksRec() : new Rectangle[0];
    }

    public Rectangle[] getHurtBoxes() {
        AnimElement elem = anim.getCurrentImageSprite();
        return elem != null ? elem.getCollisionsRec() : new Rectangle[0];
    }

    public boolean hasActiveHitbox() {
        Rectangle[] atk = getAttackBoxes();
        return atk != null && atk.length > 0;
    }

    /** 1-based AnimElem index (same as Mugen {@code AnimElem}). */
    public int getAnimElemNo() {
        return anim.getAnimElemNo();
    }

    /** Skip to a 1-based AnimElem (same as CNS {@code ChangeAnim elem=N}). */
    public void setAnimElem(int elem1Based) {
        if (elem1Based > 0) {
            anim.setAnimElem(elem1Based - 1);
        }
    }

    /** Mugen {@code AnimTime} (0 on last frame). */
    public int getAnimTime() {
        return anim.getAnimTime();
    }

    public boolean hasAction(int action) {
        try {
            return anim.getCurrentGroupSprite(action) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public void setActionIfExists(int action, int fallback) {
        if (hasAction(action)) {
            setAction(action);
        } else if (hasAction(fallback)) {
            setAction(fallback);
        } else {
            setAction(0);
        }
    }

    private int estimateActionLength(int action) {
        int ticks = 0;
        try {
            org.lee.mugen.sprite.character.AnimGroup grp = anim.getCurrentGroupSprite(action);
            if (grp == null) {
                return 24;
            }
            AnimElement[] elems = grp.getImgSprites();
            if (elems == null) {
                return 24;
            }
            for (AnimElement e : elems) {
                int d = e.getDelay();
                ticks += d > 0 ? d : 1;
            }
        } catch (Exception ignored) {
            return 24;
        }
        return ticks > 0 ? ticks : 24;
    }
}
