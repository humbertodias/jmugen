package org.lee.mugen.sprite.character;

import java.util.HashMap;
import org.lee.mugen.parser.air.AirParser;
import org.lee.mugen.sprite.base.AbstractAnimManager;

/** GWT: animation manager without fight-engine sprite lookup. */
public class SpriteAnimManager extends AbstractAnimManager {

    public SpriteAnimManager(String spriteId) {
        groupSpriteMap = new HashMap<Integer, AnimGroup>();
    }

    public SpriteAnimManager(String spriteId, AirParser airParser) {
        this(spriteId);
        build(airParser);
    }

    public SpriteAnimManager(String spriteId, HashMap<Integer, AnimGroup> groupSpriteMap) {
        this(spriteId);
        setGroupSpriteMap(groupSpriteMap);
    }

    @Override
    public void process() {
        super.process();
    }
}
