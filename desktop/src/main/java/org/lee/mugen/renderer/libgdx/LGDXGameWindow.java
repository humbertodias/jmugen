package org.lee.mugen.renderer.libgdx;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.lee.mugen.ResourceBundleHelper;
import org.lee.mugen.core.AbstractGameFight;
import org.lee.mugen.core.AbstractGameFight.DebugAction;
import org.lee.mugen.core.Game;
import org.lee.mugen.core.sound.SoundSystem;
import org.lee.mugen.input.CmdProcDispatcher;
import org.lee.mugen.input.ISpriteCmdProcess;
import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.renderer.MugenTimer;
import org.lee.mugen.renderer.libgdx.core.GDXKeyMapper;
import org.lee.mugen.renderer.libgdx.core.LGDXAudioPlayback;
import org.lee.mugen.renderer.libgdx.core.LGDXImageLoader;
import org.lee.mugen.renderer.libgdx.core.LGDXPalFxShader;
import org.lee.mugen.util.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * LibGDX implementation of GameWindow — input path mirrors {@link org.lee.mugen.renderer.lwjgl.LGameWindow}.
 */
public class LGDXGameWindow implements GameWindow, ApplicationListener, org.lee.mugen.renderer.libgdx.core.LGDXRenderContext {

    private class DebugEventManager {
        private final class Chord {
            final DebugAction action;
            final int[] keys;
            final boolean allowRepeat;
            boolean pressed;

            Chord(DebugAction action, int[] keys, boolean allowRepeat) {
                this.action = action;
                this.keys = keys;
                this.allowRepeat = allowRepeat;
            }
        }

        private final List<Chord> chords = new ArrayList<>();

        DebugEventManager() {
            add(DebugAction.SWITCH_PLAYER_DEBUG_INFO, GDXKeyMapper.KEY_LCONTROL, GDXKeyMapper.KEY_D);
            add(DebugAction.EXPLOD_DEBUG_INFO, GDXKeyMapper.KEY_LCONTROL, GDXKeyMapper.KEY_E);
            add(DebugAction.INIT_PLAYER, GDXKeyMapper.KEY_SPACE);
            add(DebugAction.SHOW_HIDE_CNS, GDXKeyMapper.KEY_LCONTROL, GDXKeyMapper.KEY_C);
            add(DebugAction.SHOW_HIDE_ATTACK_CNS, GDXKeyMapper.KEY_LCONTROL, GDXKeyMapper.KEY_X);
            addRepeat(DebugAction.INCREASE_FPS, GDXKeyMapper.KEY_LCONTROL, GDXKeyMapper.KEY_ADD);
            addRepeat(DebugAction.DECREASE_FPS, GDXKeyMapper.KEY_LCONTROL, GDXKeyMapper.KEY_SUBTRACT);
            add(DebugAction.RESET_FPS, GDXKeyMapper.KEY_LCONTROL, GDXKeyMapper.KEY_MULTIPLY);
            add(DebugAction.DEBUG_PAUSE, GDXKeyMapper.KEY_LCONTROL, GDXKeyMapper.KEY_P);
            add(DebugAction.PAUSE_PLUS_ONE_FRAME, GDXKeyMapper.KEY_LCONTROL, GDXKeyMapper.KEY_A);
            add(DebugAction.DISPLAY_HELP, GDXKeyMapper.KEY_F1);
            // macOS: F-keys often need Fn; Ctrl+H is a reliable alternate.
            add(DebugAction.DISPLAY_HELP, GDXKeyMapper.KEY_LCONTROL, GDXKeyMapper.KEY_H);
        }

        private void add(DebugAction action, int... keys) {
            chords.add(new Chord(action, keys, false));
        }

        private void addRepeat(DebugAction action, int... keys) {
            chords.add(new Chord(action, keys, true));
        }

        public void process(Game cb) {
            if (!(cb instanceof AbstractGameFight)) {
                return;
            }
            AbstractGameFight fight = (AbstractGameFight) cb;
            for (Chord chord : chords) {
                boolean all = true;
                for (int key : chord.keys) {
                    if (!isKeyDown(key)) {
                        all = false;
                        break;
                    }
                }
                if (chord.allowRepeat) {
                    if (all) {
                        fight.onDebugAction(chord.action);
                    }
                    continue;
                }
                if (all) {
                    chord.pressed = true;
                } else if (chord.pressed) {
                    chord.pressed = false;
                    fight.onDebugAction(chord.action);
                }
            }
        }
    }

    private static class CmdProcessListener {
        boolean[] areKeysPress;
        int[] keys;

        boolean[] getAreKeysPress() {
            return areKeysPress;
        }

        int[] getKeys() {
            return keys;
        }

        void setKeys(int[] keys) {
            this.keys = keys;
            areKeysPress = new boolean[keys.length];
        }
    }

    private static class SprCmdProcessListenerAction extends CmdProcessListener {
        private final ISpriteCmdProcess scp;

        SprCmdProcessListenerAction(ISpriteCmdProcess scp) {
            this.scp = scp;
            keys = scp.getKeys();
            areKeysPress = new boolean[keys.length];
        }

        ISpriteCmdProcess getScp() {
            return scp;
        }
    }

    private int width;
    private int height;
    private String title;
    private Game callback;

    private LGDXMugenTimer timer;
    private final LGDXImageLoader imageLoader = new LGDXDesktopImageLoader();
    private final LGDXPalFxShader palFxShader = new LGDXDesktopPalFxShader();
    private final GameWindow.MouseCtrl mouse = new GameWindow.MouseCtrl();
    private final Vector3 unprojectTmp = new Vector3();

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Lwjgl3Application application;

    private boolean isFinishInit = false;
    private boolean gameRunning = true;

    private final List<CmdProcessListener> cmdProcess = new LinkedList<>();
    private final List<SprCmdProcessListenerAction> spriteCmdProcess = new LinkedList<>();
    private final List<MugenKeyListener> mugenKeyListeners = new ArrayList<>();

    private final DebugEventManager debugEventManager = new DebugEventManager();

    /** Reset each frame — {@link LGDXMugenDrawer#scale} stacks on the batch transform matrix. */
    private final Matrix4 batchTransformIdentity = new Matrix4();

    /**
     * {@link OrthographicCamera#combined} for the full 320×240 view (same as {@link SpriteBatch} at frame start),
     * captured before {@code setClip} mutates the camera. Used to place LWJGL-style sub-viewports.
     */
    private final Matrix4 worldProjectionSnapshot = new Matrix4();

    public LGDXGameWindow() {
        setTitle("JMugen - LibGDX");
        setResolution(640, 480);
        timer = new LGDXMugenTimer();
    }

    private boolean isKeyDown(int key) {
        return Gdx.input.isKeyPressed(key);
    }


    @Override
    public GameWindow getGameWindow() {
        return this;
    }

    @Override
    public LGDXImageLoader getImageLoader() {
        return imageLoader;
    }

    @Override
    public LGDXPalFxShader getPalFxShader() {
        return palFxShader;
    }

    @Override
    public MugenTimer getTimer() {
        return timer;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setResolution(int x, int y) {
        this.width = x;
        this.height = y;
    }

    @Override
    public void start() throws Exception {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(width, height);
        config.setTitle(title);
        config.useVsync(true);
        config.setForegroundFPS(60);

        application = new Lwjgl3Application(this, config);
    }

    @Override
    public void setGameWindowCallback(Game callback) {
        this.callback = callback;
    }

    @Override
    public void addSpriteKeyProcessor(ISpriteCmdProcess scp) {
        spriteCmdProcess.add(new SprCmdProcessListenerAction(scp));
    }

    @Override
    public GameWindow.MouseCtrl getMouseStatus() {
        if (viewport != null) {
            unprojectTmp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            viewport.unproject(unprojectTmp);
            mouse.setX((int) unprojectTmp.x);
            mouse.setY((int) unprojectTmp.y);
        } else {
            mouse.setX(Gdx.input.getX());
            mouse.setY(Gdx.input.getY());
        }
        boolean left = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
        mouse.setLeftPress(left);
        mouse.setLeftRelease(!left);
        mouse.setRightPress(Gdx.input.isButtonPressed(Input.Buttons.RIGHT));
        return mouse;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(320, 240, camera);
        camera.setToOrtho(true, 320, 240);
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        worldProjectionSnapshot.set(camera.combined);

        SoundSystem.installAudioPlayback(new LGDXAudioPlayback());

        try {
            initKeys();
        } catch (IllegalArgumentException | SecurityException | IllegalAccessException | NoSuchFieldException e) {
            Logger.error("initKeys failed", e);
            throw new RuntimeException(e);
        }

        try {
            callback.init(LGDXGameWindow.this);
            isFinishInit = true;
        } catch (Exception e) {
            Logger.error("Error initializing game", e);
            throw new RuntimeException("Failed to initialize game callback", e);
        }
    }

    private void initKeys() throws IllegalAccessException, NoSuchFieldException {
        ResourceBundle bundle = ResourceBundleHelper.getBundle("keys");
        {
            CmdProcessListener cmdProcessListener = new CmdProcessListener();
            cmdProcessListener.setKeys(new int[]{
                    org.lee.mugen.renderer.libgdx.core.GDXKeyMapper.KEY_ESCAPE, org.lee.mugen.renderer.libgdx.core.GDXKeyMapper.KEY_F1, org.lee.mugen.renderer.libgdx.core.GDXKeyMapper.KEY_F2, org.lee.mugen.renderer.libgdx.core.GDXKeyMapper.KEY_F3,
                    org.lee.mugen.renderer.libgdx.core.GDXKeyMapper.KEY_F4, org.lee.mugen.renderer.libgdx.core.GDXKeyMapper.KEY_F5, org.lee.mugen.renderer.libgdx.core.GDXKeyMapper.KEY_F6, org.lee.mugen.renderer.libgdx.core.GDXKeyMapper.KEY_F7,
                    org.lee.mugen.renderer.libgdx.core.GDXKeyMapper.KEY_F8, org.lee.mugen.renderer.libgdx.core.GDXKeyMapper.KEY_F9, org.lee.mugen.renderer.libgdx.core.GDXKeyMapper.KEY_F10, org.lee.mugen.renderer.libgdx.core.GDXKeyMapper.KEY_F11, org.lee.mugen.renderer.libgdx.core.GDXKeyMapper.KEY_F12
            });
            cmdProcess.add(cmdProcessListener);
        }

        CmdProcDispatcher cd1 = new CmdProcDispatcher(
                keyField(bundle, "P1.UP"), keyField(bundle, "P1.DOWN"), keyField(bundle, "P1.LEFT"), keyField(bundle, "P1.RIGHT"),
                keyField(bundle, "P1.A"), keyField(bundle, "P1.B"), keyField(bundle, "P1.C"),
                keyField(bundle, "P1.X"), keyField(bundle, "P1.Y"), keyField(bundle, "P1.Z"),
                keyField(bundle, "P1.ABC"), keyField(bundle, "P1.XYZ"));
        CmdProcDispatcher.getSpriteDispatcherMap().put("1", cd1);
        {
            CmdProcessListener cmdProcessListener = new CmdProcessListener();
            cmdProcessListener.setKeys(cd1.getKeys());
            cmdProcess.add(cmdProcessListener);
        }

        CmdProcDispatcher cd2 = new CmdProcDispatcher(
                keyField(bundle, "P2.UP"), keyField(bundle, "P2.DOWN"), keyField(bundle, "P2.LEFT"), keyField(bundle, "P2.RIGHT"),
                keyField(bundle, "P2.A"), keyField(bundle, "P2.B"), keyField(bundle, "P2.C"),
                keyField(bundle, "P2.X"), keyField(bundle, "P2.Y"), keyField(bundle, "P2.Z"),
                keyField(bundle, "P2.ABC"), keyField(bundle, "P2.XYZ"));
        CmdProcDispatcher.getSpriteDispatcherMap().put("2", cd2);
        {
            CmdProcessListener cmdProcessListener = new CmdProcessListener();
            cmdProcessListener.setKeys(cd2.getKeys());
            cmdProcess.add(cmdProcessListener);
        }

        CmdProcDispatcher cd3 = new CmdProcDispatcher(
                keyField(bundle, "P3.UP"), keyField(bundle, "P3.DOWN"), keyField(bundle, "P3.LEFT"), keyField(bundle, "P3.RIGHT"),
                keyField(bundle, "P3.A"), keyField(bundle, "P3.B"), keyField(bundle, "P3.C"),
                keyField(bundle, "P3.X"), keyField(bundle, "P3.Y"), keyField(bundle, "P3.Z"),
                keyField(bundle, "P3.ABC"), keyField(bundle, "P3.XYZ"));
        CmdProcDispatcher.getSpriteDispatcherMap().put("3", cd3);
        {
            CmdProcessListener cmdProcessListener = new CmdProcessListener();
            cmdProcessListener.setKeys(cd3.getKeys());
            cmdProcess.add(cmdProcessListener);
        }

        CmdProcDispatcher cd4 = new CmdProcDispatcher(
                keyField(bundle, "P4.UP"), keyField(bundle, "P4.DOWN"), keyField(bundle, "P4.LEFT"), keyField(bundle, "P4.RIGHT"),
                keyField(bundle, "P4.A"), keyField(bundle, "P4.B"), keyField(bundle, "P4.C"),
                keyField(bundle, "P4.X"), keyField(bundle, "P4.Y"), keyField(bundle, "P4.Z"),
                keyField(bundle, "P4.ABC"), keyField(bundle, "P4.XYZ"));
        CmdProcDispatcher.getSpriteDispatcherMap().put("4", cd4);
        {
            CmdProcessListener cmdProcessListener = new CmdProcessListener();
            cmdProcessListener.setKeys(cd4.getKeys());
            cmdProcess.add(cmdProcessListener);
        }
    }

    private static int keyField(ResourceBundle bundle, String key) throws NoSuchFieldException, IllegalAccessException {
        return org.lee.mugen.renderer.libgdx.core.GDXKeyMapper.class.getDeclaredField("KEY_" + bundle.getString(key).toUpperCase()).getInt(null);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        camera.setToOrtho(true, 320, 240);
        batch.setProjectionMatrix(camera.combined);
        worldProjectionSnapshot.set(camera.combined);
    }

    @Override
    public void render() {
        if (isFinishInit && callback != null && gameRunning) {
            keyManagementExecute();
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();

        if (isFinishInit && callback != null && gameRunning) {
            try {
                int delta = (int) (Gdx.graphics.getDeltaTime() * 1000);
                callback.update(delta);

                Game another = callback.getNext();
                if (another != callback) {
                    another.init(this);
                    callback.free();
                    callback = another;
                }

                batch.setProjectionMatrix(camera.combined);
                worldProjectionSnapshot.set(camera.combined);
                batchTransformIdentity.idt();
                batch.setTransformMatrix(batchTransformIdentity);
                batch.begin();
                try {
                    callback.render();
                    if (callback instanceof AbstractGameFight) {
                        ((AbstractGameFight) callback).renderDebugInfo();
                    }
                } finally {
                    if (batch.isDrawing()) {
                        batch.end();
                    }
                }
            } catch (Exception e) {
                Logger.error("Error updating game", e);
                e.printStackTrace();
            }
        }
    }

    private void keyManagementExecute() {
        if (callback == null) {
            return;
        }
        debugEventManager.process(callback);

        for (CmdProcessListener cmd : cmdProcess) {
            boolean[] areKeysPress = cmd.getAreKeysPress();
            int[] keys = cmd.getKeys();
            for (int i = 0; i < keys.length; ++i) {
                if (!areKeysPress[i] && isKeyDown(keys[i])) {
                    areKeysPress[i] = true;
                    for (MugenKeyListener kl : mugenKeyListeners) {
                        kl.action(keys[i], true);
                    }
                } else if (areKeysPress[i] && !isKeyDown(keys[i])) {
                    areKeysPress[i] = false;
                    for (MugenKeyListener kl : mugenKeyListeners) {
                        kl.action(keys[i], false);
                    }
                }
            }
        }

        for (SprCmdProcessListenerAction sa : spriteCmdProcess) {
            boolean[] areKeysPress = sa.getAreKeysPress();
            int[] keys = sa.getKeys();
            ISpriteCmdProcess scp = sa.getScp();
            for (int i = 0; i < keys.length; ++i) {
                if (!areKeysPress[i] && isKeyDown(keys[i])) {
                    areKeysPress[i] = true;
                    scp.keyPressed(keys[i]);
                    for (MugenKeyListener kl : mugenKeyListeners) {
                        kl.action(keys[i], true);
                    }
                } else if (areKeysPress[i] && !isKeyDown(keys[i])) {
                    areKeysPress[i] = false;
                    scp.keyReleased(keys[i]);
                    for (MugenKeyListener kl : mugenKeyListeners) {
                        kl.action(keys[i], false);
                    }
                }
            }
        }
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        SoundSystem.clearAudioPlayback();
        if (batch != null) {
            batch.dispose();
        }
        if (callback != null) {
            callback.free();
        }
        gameRunning = false;
        System.exit(0);
    }

    @Override
    public int getKeyEsc() {
        return org.lee.mugen.renderer.libgdx.core.GDXKeyMapper.KEY_ESCAPE;
    }

    @Override
    public int getKeyF1() {
        return org.lee.mugen.renderer.libgdx.core.GDXKeyMapper.KEY_F1;
    }

    @Override
    public int getKeyF2() {
        return org.lee.mugen.renderer.libgdx.core.GDXKeyMapper.KEY_F2;
    }

    @Override
    public int getKeyF3() {
        return org.lee.mugen.renderer.libgdx.core.GDXKeyMapper.KEY_F3;
    }

    @Override
    public void addActionListener(MugenKeyListener al) {
        if (al != null) {
            mugenKeyListeners.add(al);
        }
    }

    @Override
    public void clearListener() {
        mugenKeyListeners.clear();
    }

    @Override
    public void setRender(boolean v) {
        gameRunning = v;
    }

    @Override
    public boolean isRender() {
        return gameRunning;
    }

    @Override
    public void removeSpriteKeysProcessors() {
        spriteCmdProcess.clear();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    /** @see #worldProjectionSnapshot */
    public Matrix4 getWorldProjectionSnapshot() {
        return worldProjectionSnapshot;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public int getGameWidth() {
        return width;
    }

    public int getGameHeight() {
        return height;
    }

    public boolean isFinishInit() {
        return isFinishInit;
    }

    public Lwjgl3Application getApplication() {
        return application;
    }
}
