package org.lee.mugen.core.gameSelect;

import org.lee.mugen.renderer.GameWindow;

/**
 * GWT replacement for desktop {@link org.lee.mugen.core.gameSelect.GameSelect}.
 */
public class GameSelect implements org.lee.mugen.core.Game {

    private static final GameSelect INSTANCE = new GameSelect();
    private final GwtGameSelect delegate = GwtGameSelect.getInstance();

    public static GameSelect getInstance() {
        return INSTANCE;
    }

    private GameSelect() {
    }

    @Override
    public void free() {
        delegate.free();
    }

    @Override
    public org.lee.mugen.core.Game getNext() throws Exception {
        return delegate.getNext();
    }

    @Override
    public void init(GameWindow container) throws Exception {
        delegate.init(container);
    }

    @Override
    public void reInit(GameWindow container) throws Exception {
        delegate.reInit(container);
    }

    @Override
    public void render() throws Exception {
        delegate.render();
    }

    @Override
    public void update(int delta) throws Exception {
        delegate.update(delta);
    }
}
