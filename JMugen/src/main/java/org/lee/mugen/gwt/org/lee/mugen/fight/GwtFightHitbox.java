package org.lee.mugen.fight;

import org.lee.mugen.object.Rectangle;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;

/**
 * Clsn1/Clsn2 overlap with AIR boxes relative to the character axis (feet/origin).
 *
 * <p>Keeps the same origin as {@link #spriteDrawPos} so collision matches the drawn sprite.
 */
final class GwtFightHitbox {

    private GwtFightHitbox() {}

    static boolean attackHits(
            GwtFightFighterAnim attackerAnim,
            float attackerX,
            float attackerY,
            int attackerFacing,
            GwtFightFighterAnim defenderAnim,
            float defenderX,
            float defenderY,
            int defenderFacing) {
        Rectangle[] attack = attackerAnim.getAttackBoxes();
        Rectangle[] hurt = defenderAnim.getHurtBoxes();
        if (attack == null || attack.length == 0 || hurt == null || hurt.length == 0) {
            return false;
        }
        float[] aOrigin = axisOrigin(attackerAnim, attackerX, attackerY, attackerFacing);
        float[] dOrigin = axisOrigin(defenderAnim, defenderX, defenderY, defenderFacing);
        for (Rectangle a : attack) {
            if (a == null) {
                continue;
            }
            float[] ar = toAxisBox(a, aOrigin[0], aOrigin[1], attackerFacing);
            for (Rectangle h : hurt) {
                if (h == null) {
                    continue;
                }
                float[] hr = toAxisBox(h, dOrigin[0], dOrigin[1], defenderFacing);
                if (ar[0] < hr[2] && ar[2] > hr[0] && ar[1] < hr[3] && ar[3] > hr[1]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Character axis in world space (AIR offsets included so boxes follow the anim).
     * Returns {@code {axisX, axisY}}.
     */
    static float[] axisOrigin(GwtFightFighterAnim anim, float x, float y, int facing) {
        // AIR x/y offset is relative to facing (same as previous GWT fight stub).
        float ox = x + anim.getXOffset() * facing;
        float oy = y + anim.getYOffset();
        return new float[] {ox, oy};
    }

    /**
     * Top-left of the sprite quad so the SFF axis sits on {@code axisOrigin}, with UV flip for
     * facing (same contract as desktop {@code DrawProperties} flipH).
     * Returns {@code {drawX, drawY, flipH as 1/0}}.
     */
    static float[] spriteDrawPos(
            GwtFightFighterAnim anim, float x, float y, int facing, float cameraX, int zoffset) {
        ImageContainer img = anim.getCurrentImage();
        ImageSpriteSFF sff = anim.getCurrentImageSpriteSFF();
        int w = img != null ? img.getWidth() : 0;
        int h = img != null ? img.getHeight() : 0;
        int xAxis = sff != null ? sff.getXAxis() : (w / 2);
        int yAxis = sff != null ? sff.getYAxis() : h;
        boolean flipH = (facing == -1) ^ anim.isMirrorH();
        float[] origin = axisOrigin(anim, x, y, facing);
        float axisScreenX = 160f + origin[0] + cameraX;
        float axisScreenY = zoffset + origin[1];
        // UV horizontal flip keeps the dest quad fixed; shift left edge so axis stays put.
        float drawX = flipH ? axisScreenX - (w - xAxis) : axisScreenX - xAxis;
        float drawY = axisScreenY - yAxis;
        return new float[] {drawX, drawY, flipH ? 1f : 0f};
    }

    /** World AABB {@code {x1,y1,x2,y2}} from an axis-relative AIR rectangle. */
    static float[] toAxisBox(Rectangle r, float axisX, float axisY, int facing) {
        float x1 = r.getX1();
        float x2 = r.getX2();
        if (facing < 0) {
            x1 = -x1;
            x2 = -x2;
        }
        float left = Math.min(x1, x2) + axisX;
        float right = Math.max(x1, x2) + axisX;
        float top = Math.min(r.getY1(), r.getY2()) + axisY;
        float bottom = Math.max(r.getY1(), r.getY2()) + axisY;
        return new float[] {left, top, right, bottom};
    }
}
