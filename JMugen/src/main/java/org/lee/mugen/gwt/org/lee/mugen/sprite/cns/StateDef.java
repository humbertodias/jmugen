package org.lee.mugen.sprite.cns;

import java.io.Serializable;

/** GWT compile stub. */
public class StateDef implements Serializable {

    private String id;

    public StateDef() {
        this("0");
    }

    public StateDef(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getIntId() {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
