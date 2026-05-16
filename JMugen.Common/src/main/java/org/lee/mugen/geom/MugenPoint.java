package org.lee.mugen.geom;

import java.io.Serializable;

/**
 * Integer 2D point (replaces {@link java.awt.Point} for Android where AWT is absent).
 */
public final class MugenPoint implements Cloneable, Serializable {

    public int x;
    public int y;

    public MugenPoint() {
    }

    public MugenPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public MugenPoint clone() {
        return new MugenPoint(x, y);
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance(MugenPoint other) {
        return distance(this.x, this.y, other.x, other.y);
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.hypot(x2 - x1, y2 - y1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MugenPoint)) {
            return false;
        }
        MugenPoint m = (MugenPoint) o;
        return x == m.x && y == m.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
