package org.lee.mugen.fight;

import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.parser.air.AirParser;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.sff.SffReader;
import org.lee.mugen.sprite.base.AbstractAnimManager;
import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;
import org.lee.mugen.sprite.character.AnimElement;
import org.lee.mugen.sprite.character.SpriteAnimManager;
import org.lee.mugen.sprite.parser.Parser;

/** GWT: stand animation (action 0) for in-fight character display. */
public final class GwtFightFighterAnim {

    private final AbstractAnimManager anim;
    private final SpriteSFF sff;

    public GwtFightFighterAnim(String characterId, String sffFile, String airFile) throws Exception {
        String sffPath =
                Parser.getExistFile(JMugenConstant.RESOURCE + "chars/" + characterId + "/" + sffFile);
        sff = new SpriteSFF(new SffReader(sffPath, null), true, false);
        String airPath =
                Parser.getExistFile(JMugenConstant.RESOURCE + "chars/" + characterId + "/" + airFile);
        anim = new SpriteAnimManager(characterId, new AirParser(airPath));
        anim.setAction(0);
    }

    public void setAction(int action) {
        if (anim.getAction() != action) {
            anim.setAction(action);
        }
    }

    public void process() {
        anim.process();
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
}
