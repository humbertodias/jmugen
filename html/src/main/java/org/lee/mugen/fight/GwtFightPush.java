package org.lee.mugen.fight;

import org.lee.mugen.object.Rectangle;

/**
 * Player-vs-player body push. Symmetric separation so either side can shove the other
 * (avoids the one-sided “P2 stuck, P1 never moves” of move-other-first).
 */
public final class GwtFightPush {

    private static final float GROUND_FRONT = 16f;
    private static final float GROUND_BACK = 15f;
    private static final float AIR_FRONT = 12f;
    private static final float AIR_BACK = 12f;
    private static final float DEFAULT_HEIGHT = 60f;

    private GwtFightPush() {}

    public static void resolve(GwtFightPlayer a, GwtFightPlayer b) {
        if (a == null || b == null) {
            return;
        }
        PushBox ra = a.getPushBox();
        PushBox rb = b.getPushBox();
        if (!intersects(ra, rb)) {
            return;
        }
        float overlap = Math.min(ra.x2, rb.x2) - Math.max(ra.x1, rb.x1);
        if (overlap <= 0f) {
            return;
        }

        GwtFightPlayer left = a.getWorldX() <= b.getWorldX() ? a : b;
        GwtFightPlayer right = left == a ? b : a;

        float half = overlap * 0.5f;
        float leftBefore = left.getWorldX();
        float rightBefore = right.getWorldX();

        left.addWorldX(-half);
        right.addWorldX(half);

        // If stage/screen clamp blocked one side, dump the leftover onto the other.
        float leftMoved = leftBefore - left.getWorldX();
        float rightMoved = right.getWorldX() - rightBefore;
        float leftShort = half - leftMoved;
        float rightShort = half - rightMoved;
        if (leftShort > 0.01f) {
            right.addWorldX(leftShort);
        }
        if (rightShort > 0.01f) {
            left.addWorldX(-rightShort);
        }
    }

    static PushBox buildPushBox(
            float worldX,
            float worldY,
            int facing,
            boolean onGround,
            Rectangle[] hurtBoxes,
            float animXOff,
            float animYOff) {
        float baseX = worldX + animXOff * facing;
        float baseY = worldY + animYOff;
        if (hurtBoxes != null && hurtBoxes.length > 0) {
            Float x1 = null;
            Float y1 = null;
            Float x2 = null;
            Float y2 = null;
            for (Rectangle r : hurtBoxes) {
                if (r == null) {
                    continue;
                }
                int lx1 = mapX(r.getX1(), facing) + (int) baseX;
                int lx2 = mapX(r.getX2(), facing) + (int) baseX;
                int ly1 = r.getY1() + (int) baseY;
                int ly2 = r.getY2() + (int) baseY;
                if (lx1 > lx2) {
                    int t = lx1;
                    lx1 = lx2;
                    lx2 = t;
                }
                if (ly1 > ly2) {
                    int t = ly1;
                    ly1 = ly2;
                    ly2 = t;
                }
                if (x1 == null) {
                    x1 = (float) lx1;
                    y1 = (float) ly1;
                    x2 = (float) lx2;
                    y2 = (float) ly2;
                } else {
                    x1 = Math.min(x1, lx1);
                    y1 = Math.min(y1, ly1);
                    x2 = Math.max(x2, lx2);
                    y2 = Math.max(y2, ly2);
                }
            }
            if (x1 != null) {
                return new PushBox(x1, y1, x2, y2);
            }
        }
        float front = onGround ? GROUND_FRONT : AIR_FRONT;
        float back = onGround ? GROUND_BACK : AIR_BACK;
        float left = facing == 1 ? worldX - back : worldX - front;
        float right = facing == 1 ? worldX + front : worldX + back;
        return new PushBox(left, worldY - DEFAULT_HEIGHT, right, worldY);
    }

    private static int mapX(int localX, int facing) {
        return facing == -1 ? -localX : localX;
    }

    private static boolean intersects(PushBox a, PushBox b) {
        return a.x1 < b.x2 && a.x2 > b.x1 && a.y1 < b.y2 && a.y2 > b.y1;
    }

    static final class PushBox {
        final float x1;
        final float y1;
        final float x2;
        final float y2;

        PushBox(float x1, float y1, float x2, float y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}
