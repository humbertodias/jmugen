package org.lee.mugen.fight.section.elem;

import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;
import org.lee.mugen.util.BeanTools;

/** GWT: title sprites without reflection. */
public class SprType extends CommonType {

    int spritegrp;
    int spriteno;
    Object root;

    public SprType(int spritegrp, int spriteno, Object root) {
        this.spritegrp = spritegrp;
        this.spriteno = spriteno;
        this.root = root;
    }

    public SprType(Object root) {
        this.root = root;
    }

    @Override
    public void parse(String name, String value) {
        if (name.equals("spr")) {
            int[] res = (int[]) BeanTools.getConvertersMap().get(int[].class).convert(value);
            spritegrp = res[0];
            spriteno = res[1];
        }
    }

    public ImageSpriteSFF getImage() {
        SpriteSFF sff = resolveSpriteSff();
        if (sff == null || sff.getGroupSpr(spritegrp) == null
                || sff.getGroupSpr(spritegrp).getImgSpr(spriteno) == null) {
            return null;
        }
        return sff.getGroupSpr(spritegrp).getImgSpr(spriteno);
    }

    private SpriteSFF fightSff;

    public void bindRoot(Object root) {
        this.root = root;
    }

    private SpriteSFF resolveSpriteSff() {
        if (root instanceof org.lee.mugen.fight.GwtFightSprRoot) {
            return ((org.lee.mugen.fight.GwtFightSprRoot) root).getSpr();
        }
        if (fightSff != null) {
            return fightSff;
        }
        if (root instanceof MugenSystem) {
            return ((MugenSystem) root).getFiles().getSpr();
        }
        if (root instanceof org.lee.mugen.fight.system.Files) {
            return ((org.lee.mugen.fight.system.Files) root).getSpr();
        }
        return null;
    }

    public void setFightSff(SpriteSFF fightSff) {
        this.fightSff = fightSff;
    }

    public int getSpritegrp() {
        return spritegrp;
    }

    public void setSpritegrp(int spritegrp) {
        this.spritegrp = spritegrp;
    }

    public int getSpriteno() {
        return spriteno;
    }

    public void setSpriteno(int spriteno) {
        this.spriteno = spriteno;
    }
}
