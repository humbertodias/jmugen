package org.lee.mugen.background;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.lee.mugen.geom.MugenPoint;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.parser.air.AirParser;
import org.lee.mugen.sprite.base.AbstractAnimManager;
import org.lee.mugen.sprite.entity.PointF;
import org.lee.mugen.sprite.entity.SprGrpNum;
import org.lee.mugen.sprite.parser.Parser.GroupText;
import org.lee.mugen.util.BeanTools;

/**
 * GWT title backgrounds: no regex in section matching or BG field expressions.
 */
public class GwtBackground extends Background {

    private final String bgdefSection;
    private final String bgSectionPrefix;

    public GwtBackground(Object parent, File currentDir, String prefix) {
        super(parent, currentDir, " ", " ", " ", " ");
        bgdefSection = prefix + "bgdef";
        bgSectionPrefix = prefix + "bg ";
    }

    @Override
    public void parse(Object root, List<GroupText> groups) throws Exception {
        List<BG> bgs = new ArrayList<BG>();
        BGdef bgdef = null;

        for (GroupText grp : groups) {
            String section = grp.getSection();
            if (section == null) {
                continue;
            }
            String s = section.toLowerCase();

            if (bgdefSection.equals(s)) {
                bgdef = new BGdef();
                for (String key : grp.getKeysOrdered()) {
                    String value = grp.getKeyValues().get(key);
                    if ("bgclearcolor".equalsIgnoreCase(key)) {
                        bgdef.setBgclearcolor((RGB) BeanTools.getConvertersMap().get(RGB.class).convert(value));
                    } else if ("debugbg".equalsIgnoreCase(key)) {
                        bgdef.setDebugbg(Integer.parseInt(value.trim()));
                    } else if ("spr".equalsIgnoreCase(key)) {
                        bgdef.parse(this, key, value, false);
                    }
                }
            } else if (s.startsWith(bgSectionPrefix)) {
                String bgName = s.substring(bgSectionPrefix.length()).trim();
                BG bg = new BG(this);
                bg.setName(bgName);
                bgs.add(bg);
                fillBgFields(bg, grp);
            }
        }
        if (bgdef == null) {
            bgdef = new BGdef();
        }
        initTitleParseResult(bgdef, bgs, new AbstractAnimManager(new AirParser()));
    }

    private static void fillBgFields(BG bg, GroupText grp) throws Exception {
        for (String key : grp.getKeyValues().keySet()) {
            String value = grp.getKeyValues().get(key);
            Object[] objectValues = BgExpression.eval(key, value);
            applyToBg(bg, key, objectValues);
        }
    }

    private static void applyToBg(BG bg, String key, Object[] objectValues) {
        if (objectValues == null || objectValues.length == 0) {
            return;
        }
        String k = key.toLowerCase();
        if (objectValues.length == 1) {
            if (applyScalar(bg, k, objectValues[0])) {
                return;
            }
            applyOne(bg, k, objectValues[0]);
            return;
        }
        if ("start".equals(k) || "tile".equals(k) || "velocity".equals(k) || "delta".equals(k)) {
            PointF p = new PointF();
            if (objectValues[0] instanceof Number) {
                p.setX(((Number) objectValues[0]).floatValue());
            }
            if (objectValues.length > 1 && objectValues[1] instanceof Number) {
                p.setY(((Number) objectValues[1]).floatValue());
            }
            applyOne(bg, k, p);
        } else if ("width".equals(k)) {
            int wx = objectValues[0] instanceof Number ? ((Number) objectValues[0]).intValue() : 0;
            int wy = objectValues.length > 1 && objectValues[1] instanceof Number
                    ? ((Number) objectValues[1]).intValue() : 0;
            bg.setWidth(new MugenPoint(wx, wy));
        } else if ("spriteno".equals(k) && objectValues[0] instanceof SprGrpNum) {
            bg.setSpriteno((SprGrpNum) objectValues[0]);
        } else {
            applyOne(bg, k, objectValues[0]);
        }
    }

    /** Single-number {@code velocity = -1}, {@code tile = 1}, etc. */
    private static boolean applyScalar(BG bg, String k, Object value) {
        if (!(value instanceof Number)) {
            return false;
        }
        float n = ((Number) value).floatValue();
        if ("velocity".equals(k)) {
            bg.getVelocity().setX(n);
            return true;
        }
        if ("tile".equals(k)) {
            bg.getTile().setX(n);
            return true;
        }
        if ("start".equals(k)) {
            bg.getStart().setX(n);
            return true;
        }
        if ("delta".equals(k)) {
            bg.getDelta().setX(n);
            return true;
        }
        return false;
    }

    private static void applyOne(BG bg, String k, Object value) {
        if (value == null) {
            return;
        }
        if ("type".equals(k) && value instanceof String) {
            bg.setType((String) value);
        } else if ("trans".equals(k) && value instanceof String) {
            bg.setTrans((String) value);
        } else if ("start".equals(k) && value instanceof PointF) {
            bg.setStart((PointF) value);
        } else if ("tile".equals(k) && value instanceof PointF) {
            bg.setTile((PointF) value);
        } else if ("velocity".equals(k) && value instanceof PointF) {
            bg.setVelocity((PointF) value);
        } else if ("delta".equals(k) && value instanceof PointF) {
            bg.setDelta((PointF) value);
        } else if ("width".equals(k) && value instanceof MugenPoint) {
            bg.setWidth((MugenPoint) value);
        } else if ("spriteno".equals(k) && value instanceof SprGrpNum) {
            bg.setSpriteno((SprGrpNum) value);
        } else if ("mask".equals(k) && value instanceof Number) {
            bg.setMask(((Number) value).intValue());
        } else if ("layerno".equals(k) && value instanceof Number) {
            bg.setLayerno(((Number) value).intValue());
        } else if ("actionno".equals(k) && value instanceof Number) {
            bg.setActionno(((Number) value).intValue());
        } else if ("yscalestart".equals(k) && value instanceof Number) {
            bg.setYscalestart(((Number) value).floatValue());
        } else if ("yscaledelta".equals(k) && value instanceof Number) {
            bg.setYscaledelta(((Number) value).floatValue());
        }
    }
}
