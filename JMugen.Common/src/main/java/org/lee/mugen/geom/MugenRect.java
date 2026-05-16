package org.lee.mugen.geom;

import java.io.Serializable;

/**
 * Axis-aligned integer rectangle (replaces {@link java.awt.Rectangle} for Android where AWT is absent).
 */
public final class MugenRect implements Cloneable, Serializable {

    public int x;
    public int y;
    public int width;
    public int height;

    public MugenRect() {
    }

    public MugenRect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public MugenRect(MugenRect r) {
        this(r.x, r.y, r.width, r.height);
    }

    @Override
    public MugenRect clone() {
        return new MugenRect(x, y, width, height);
    }

    public void setLocation(int nx, int ny) {
        this.x = nx;
        this.y = ny;
    }

    public void translate(int dx, int dy) {
        x += dx;
        y += dy;
    }

    /** Union with {@code r}, matching {@link java.awt.Rectangle#add(java.awt.Rectangle)}. */
    public void add(MugenRect r) {
        int x1 = Math.min(x, r.x);
        int y1 = Math.min(y, r.y);
        int x2 = Math.max(x + width, r.x + r.width);
        int y2 = Math.max(y + height, r.y + r.height);
        x = x1;
        y = y1;
        width = x2 - x1;
        height = y2 - y1;
    }

    public boolean intersects(MugenRect r) {
        return width > 0
            && height > 0
            && r.width > 0
            && r.height > 0
            && r.x < x + width
            && r.x + r.width > x
            && r.y < y + height
            && r.y + r.height > y;
    }

    public MugenRect intersection(MugenRect r) {
        int nx = Math.max(x, r.x);
        int ny = Math.max(y, r.y);
        int nw = Math.min(x + width, r.x + r.width) - nx;
        int nh = Math.min(y + height, r.y + r.height) - ny;
        if (nw < 0) {
            nw = 0;
        }
        if (nh < 0) {
            nh = 0;
        }
        return new MugenRect(nx, ny, nw, nh);
    }
}
