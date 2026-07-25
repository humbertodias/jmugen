package org.lee.mugen.fight.section.elem;

import java.util.HashMap;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.sprite.base.AbstractAnimManager;
import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;
import org.lee.mugen.sprite.character.AnimElement;
import org.lee.mugen.sprite.character.AnimGroup;

/** GWT: title/menu rendering without reflection. */
public class AnimType extends CommonType {

    int action;
    Object from;
    AbstractAnimManager anim;

    public AnimType(int action, Object animGetter) {
        this.action = action;
        this.from = animGetter;
    }

    public AnimType(Object animGetter) {
        this.from = animGetter;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    @Override
    public void parse(String name, String value) {
        if (name.equals("anim")) {
            action = Integer.parseInt(value);
        }
    }

    public AbstractAnimManager getAnim() {
        if (anim == null && from instanceof HasAnimManager) {
            AbstractAnimManager animManager = ((HasAnimManager) from).getAnim();
            HashMap<Integer, AnimGroup> aMap = animManager.getGroupSpriteMap();
            anim = new AbstractAnimManager(aMap);
            anim.setAction(action);
        }
        return anim;
    }

    public void process() {
        if (getAnim() != null) {
            getAnim().process();
        }
    }

    public ImageSpriteSFF getImage() {
        AnimElement animElem = getAnim().getCurrentImageSprite();
        if (animElem == null || animElem.getAirData().getGrpNum() == -1) {
            return null;
        }
        SpriteSFF sff = resolveSpriteSff();
        if (sff == null) {
            return null;
        }
        return sff.getGroupSpr(animElem.getAirData().getGrpNum()).getImgSpr(animElem.getAirData().getImgNum());
    }

    private SpriteSFF resolveSpriteSff() {
        if (from instanceof HasSpriteSff) {
            return ((HasSpriteSff) from).getSpriteSff();
        }
        if (from instanceof MugenSystem) {
            return ((MugenSystem) from).getFiles().getSpr();
        }
        return null;
    }

    public interface HasAnimManager {
        AbstractAnimManager getAnim();
    }

    public interface HasSpriteSff {
        SpriteSFF getSpriteSff();
    }
}
