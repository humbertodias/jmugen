package org.lee.mugen.renderer.libgdx.web;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.lee.mugen.core.Game;
import org.lee.mugen.core.sound.SoundSystem;
import org.lee.mugen.io.MugenDataStreams;
import org.lee.mugen.renderer.GameWindow;

/**
 * Browser title screen using real {@code data/system.def} until the full {@link org.lee.mugen.core.GameMenu}
 * stack compiles on GWT.
 */
public class LGDXWebTitleGame implements Game {

    private static final String SYSTEM_DEF = LGDXWebPlatform.DATA_PREFIX + "data/system.def";

    private final List<String> menuItems = new ArrayList<String>();
    private String titleBgm;
    private int selected;
    private int elapsed;
    private LGDXMugenDrawer drawer;

    @Override
    public void init(GameWindow container) throws Exception {
        drawer = LibGDXWebRendererFactory.getDrawer();
        loadSystemDef();
        if (titleBgm != null && !titleBgm.isEmpty()) {
            SoundSystem.SoundBackGround.playMusic(LGDXWebPlatform.DATA_PREFIX + titleBgm);
        }
    }

    @Override
    public void reInit(GameWindow container) throws Exception {
        selected = 0;
    }

    @Override
    public void update(int delta) throws Exception {
        elapsed += delta;
        int n = menuItems.size();
        if (n == 0) {
            return;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            selected = (selected + n - 1) % n;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            selected = (selected + 1) % n;
        }
    }

    @Override
    public void render() throws Exception {
        if (drawer == null) {
            return;
        }
        float pulse = (float) ((Math.sin(elapsed / 280.0) + 1.0) * 0.5);
        drawer.setColor(0.02f, 0.03f, 0.1f, 1f);
        drawer.fillRect(0, 0, 320, 240);
        drawer.setColor(0.1f, 0.35f + pulse * 0.2f, 0.85f, 1f);
        drawer.drawRect(12, 12, 296, 216);

        float y = 72f;
        for (int i = 0; i < menuItems.size(); i++) {
            if (i == selected) {
                drawer.setColor(1f, 0.85f, 0.2f, 0.95f);
                drawer.fillRect(40, y - 6, 240, 18);
            }
            drawer.setColor(i == selected ? 0.05f : 0.85f, i == selected ? 0.05f : 0.9f, i == selected ? 0.1f : 1f, 1f);
            drawer.fillRect(48, y, 224, 12);
            y += 22f;
        }

        if (menuItems.isEmpty()) {
            drawer.setColor(1f, 0.4f, 0.35f, 1f);
            drawer.fillRect(48, 110, 224, 12);
        }
    }

    @Override
    public Game getNext() throws Exception {
        return this;
    }

    @Override
    public void free() {
        SoundSystem.SoundBackGround.stopMusic();
    }

    private void loadSystemDef() throws IOException {
        menuItems.clear();
        titleBgm = null;
        Reader reader = MugenDataStreams.openUtf8Reader(SYSTEM_DEF);
        try {
            BufferedReader br = new BufferedReader(reader);
            String section = "";
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith(";")) {
                    continue;
                }
                if (line.startsWith("[") && line.endsWith("]")) {
                    section = line.substring(1, line.length() - 1).trim().toLowerCase();
                    continue;
                }
                int eq = line.indexOf('=');
                if (eq < 0) {
                    continue;
                }
                String key = line.substring(0, eq).trim().toLowerCase();
                String value = line.substring(eq + 1).trim();
                if ("title info".equals(section) && key.startsWith("menu.itemname")) {
                    menuItems.add(value);
                } else if ("music".equals(section) && "title.bgm".equals(key)) {
                    titleBgm = value.replace('\\', '/');
                }
            }
        } finally {
            reader.close();
        }
        if (menuItems.isEmpty()) {
            menuItems.add("JMugen Web");
            menuItems.add("(sem entradas em system.def)");
        }
    }
}
