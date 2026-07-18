package org.lee.mugen.fight.system.elem;

import org.lee.mugen.fight.section.Section;
import org.lee.mugen.geom.MugenPoint;
import org.lee.mugen.geom.MugenRect;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.util.BeanTools;

/** GWT: same API as desktop {@link org.lee.mugen.fight.system.elem.StageDisplay}. */
public class StageDisplay implements Section {

    private boolean enable;
    private MugenPoint pos;
    private MugenRect rectangle;
    private PointF scale;
    private MugenPoint camera;

    @Override
    public void parse(Object root, String name, String value) throws Exception {
        if ("enable".equals(name)) {
            enable = Integer.parseInt(value) != 0;
        } else if ("pos".equals(name)) {
            pos = (MugenPoint) BeanTools.getConvertersMap().get(MugenPoint.class).convert(value);
        } else if ("rectangle".equals(name)) {
            rectangle = (MugenRect) BeanTools.getConvertersMap().get(MugenRect.class).convert(value);
        } else if ("scale".equals(name)) {
            scale = (PointF) BeanTools.getConvertersMap().get(PointF.class).convert(value);
        } else if ("camera".equals(name)) {
            camera = (MugenPoint) BeanTools.getConvertersMap().get(MugenPoint.class).convert(value);
        }
    }

    public boolean isEnable() {
        return enable;
    }

    public MugenPoint getPos() {
        return pos;
    }

    public MugenRect getRectangle() {
        return rectangle;
    }

    public PointF getScale() {
        return scale;
    }

    public MugenPoint getCamera() {
        return camera;
    }
}
