package org.lee.mugen.fight.system.elem;

import org.lee.mugen.fight.section.Section;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.geom.MugenPoint;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.util.BeanTools;

/** GWT: player slot on the versus screen ({@code p1.*} / {@code p2.*} in system.def). */
public class GwtPlayerVsScreen implements Section {

    private MugenPoint pos = new MugenPoint();
    private int facing = 1;
    private PointF scale = new PointF(1, 1);
    private Type name;

    @Override
    public void parse(Object root, String name, String value) throws Exception {
        if (name.startsWith("name.")) {
            if (this.name == null) {
                this.name = new Type();
            }
            this.name.setType(Type.getNext(name), this.name, value, root);
            this.name.parse(Type.getNext(name), value);
        } else if ("pos".equals(name)) {
            pos = (MugenPoint) BeanTools.getConvertersMap().get(MugenPoint.class).convert(value);
        } else if ("facing".equals(name)) {
            facing = Integer.parseInt(value.trim());
        } else if ("scale".equals(name)) {
            scale = (PointF) BeanTools.getConvertersMap().get(PointF.class).convert(value);
        }
    }

    public MugenPoint getPos() {
        return pos;
    }

    public int getFacing() {
        return facing;
    }

    public PointF getScale() {
        return scale;
    }

    public Type getName() {
        return name;
    }
}
