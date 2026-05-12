package org.lee.mugen.renderer.libgdx;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.lee.mugen.ResourceBundleHelper;
import org.lee.mugen.core.AbstractGameFight;
import org.lee.mugen.core.Game;
import org.lee.mugen.core.AbstractGameFight.DebugAction;
import org.lee.mugen.input.CmdProcDispatcher;
import org.lee.mugen.input.ISpriteCmdProcess;
import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.renderer.MugenTimer;
import org.lee.mugen.util.Logger;

import java.util.*;

/**
 * LibGDX implementation of GameWindow interface
 * Uses LWJGL3 backend for cross-platform rendering
 */
public class LGDXGameWindow implements GameWindow, ApplicationListener {

    private int width;
    private int height;
    private String title;
    private Game callback;
    
    private LGDXMugenTimer timer;
    private MouseCtrl mouse;
    
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Lwjgl3Application application;
    
    private boolean isFinishInit = false;
    private boolean gameRunning = true;
    
    private List<CmdProcessListener> cmdProcess = new LinkedList<>();
    private List<SprCmdProcessListenerAction> spriteCmdProcess = new LinkedList<>();
    private Map<Integer, Boolean> keyStates = new HashMap<>();
    private List<MugenKeyListener> mugenKeyListeners = new LinkedList<>();
    
    private DebugEventManager debugEventManager;
    
    private static class CmdProcessListener {
        boolean[] areKeysPress;
        int[] keys;
        
        public CmdProcessListener(int[] keys) {
            this.keys = keys;
            this.areKeysPress = new boolean[keys.length];
        }
    }
    
    private static class SprCmdProcessListenerAction {
        ISpriteCmdProcess listener;
        
        public SprCmdProcessListenerAction(ISpriteCmdProcess listener) {
            this.listener = listener;
        }
    }
    
    private class DebugEventManager {
        private Map<DebugAction, int[]> actionKeyMap = new HashMap<>();
        private Map<DebugAction, Boolean> actionPressMap = new HashMap<>();
        
        public DebugEventManager() {
            addAction(DebugAction.SWITCH_PLAYER_DEBUG_INFO, new int[]{Input.Keys.CONTROL_LEFT, Input.Keys.D});
            addAction(DebugAction.EXPLOD_DEBUG_INFO, new int[]{Input.Keys.CONTROL_LEFT, Input.Keys.E});
            addAction(DebugAction.INIT_PLAYER, new int[]{Input.Keys.SPACE});
            addAction(DebugAction.SHOW_HIDE_CNS, new int[]{Input.Keys.CONTROL_LEFT, Input.Keys.C});
            addAction(DebugAction.SHOW_HIDE_ATTACK_CNS, new int[]{Input.Keys.CONTROL_LEFT, Input.Keys.X});
            addAction(DebugAction.INCREASE_FPS, new int[]{Input.Keys.CONTROL_LEFT, Input.Keys.PLUS}, true);
            addAction(DebugAction.DECREASE_FPS, new int[]{Input.Keys.CONTROL_LEFT, Input.Keys.MINUS}, true);
            addAction(DebugAction.RESET_FPS, new int[]{Input.Keys.CONTROL_LEFT, Input.Keys.STAR}, true);
            addAction(DebugAction.DEBUG_PAUSE, new int[]{Input.Keys.CONTROL_LEFT, Input.Keys.P});
            addAction(DebugAction.PAUSE_PLUS_ONE_FRAME, new int[]{Input.Keys.CONTROL_LEFT, Input.Keys.A});
            addAction(DebugAction.DISPLAY_HELP, new int[]{Input.Keys.F1});
        }
        
        private void addAction(DebugAction action, int[] keys) {
            addAction(action, keys, false);
        }
        
        private void addAction(DebugAction action, int[] keys, boolean isAllowKeyRepeat) {
            actionKeyMap.put(action, keys);
            if (!isAllowKeyRepeat)
                actionPressMap.put(action, false);
        }
        
        public void process(Game callback) {
            for (DebugAction action : actionKeyMap.keySet()) {
                boolean isAllKeyOk = true;
                for (int key : actionKeyMap.get(action)) {
                    isAllKeyOk = isAllKeyOk && Gdx.input.isKeyPressed(key);
                }
                if (isAllKeyOk && actionPressMap.get(action) == null) {
                    if (callback instanceof AbstractGameFight) {
                        ((AbstractGameFight) callback).onDebugAction(action);
                    }
                    continue;
                }
                if (isAllKeyOk) {
                    actionPressMap.put(action, true);
                } else if (actionPressMap.get(action) != null && actionPressMap.get(action)) {
                    actionPressMap.put(action, false);
                    if (callback instanceof AbstractGameFight) {
                        ((AbstractGameFight) callback).onDebugAction(action);
                    }
                }
            }
        }
    }
    
    public LGDXGameWindow() {
        setTitle("JMugen - LibGDX");
        setResolution(640, 480);
        timer = new LGDXMugenTimer();
        mouse = new MouseCtrl();
        debugEventManager = new DebugEventManager();
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
    public MouseCtrl getMouseStatus() {
        mouse.setX((int) Gdx.input.getX());
        mouse.setY((int) Gdx.input.getY());
        mouse.setLeftPress(Gdx.input.isButtonPressed(Input.Buttons.LEFT));
        mouse.setRightPress(Gdx.input.isButtonPressed(Input.Buttons.RIGHT));
        return mouse;
    }
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        // Use FitViewport for proper scaling of 320x240 logical resolution to window
        viewport = new FitViewport(320, 240, camera);
        // Set camera to Y-down coordinate system to match Mugen
        camera.setToOrtho(true, 320, 240);
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        
        try {
            callback.init(LGDXGameWindow.this);
            isFinishInit = true;
        } catch (Exception e) {
            Logger.error("Error initializing game", e);
            throw new RuntimeException("Failed to initialize game callback", e);
        }
    }
    
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        // Ensure camera maintains Y-down coordinate system
        camera.setToOrtho(true, 320, 240);
        batch.setProjectionMatrix(camera.combined);
    }
    
    @Override
    public void render() {
        // Process input
        processInput();
        
        // Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // Update viewport
        viewport.apply();
        
        // Update and render game
        if (isFinishInit && callback != null && gameRunning) {
            try {
                int delta = (int) (Gdx.graphics.getDeltaTime() * 1000);
                callback.update(delta);
                if (batch != null) {
                    batch.setProjectionMatrix(camera.combined);
                    batch.begin();
                    callback.render();
                    batch.end();
                } else {
                    callback.render();
                }
            } catch (Exception e) {
                Logger.error("Error updating game", e);
            }
        }
    }
    
    private void processInput() {
        // Process sprite commands
        for (SprCmdProcessListenerAction action : spriteCmdProcess) {
            for (int key : getAllPressedKeys()) {
                action.listener.keyPressed(key);
            }
        }
        
        // Process debug actions
        if (callback != null) {
            debugEventManager.process(callback);
        }
    }
    
    private int[] getAllPressedKeys() {
        List<Integer> pressedKeys = new ArrayList<>();
        for (int key = 0; key < 256; key++) {
            if (Gdx.input.isKeyPressed(key)) {
                pressedKeys.add(key);
            }
        }
        int[] result = new int[pressedKeys.size()];
        for (int i = 0; i < pressedKeys.size(); i++) {
            result[i] = pressedKeys.get(i);
        }
        return result;
    }
    
    private void processListenerInput() {
        for (int key = 0; key < 256; key++) {
            boolean pressed = Gdx.input.isKeyPressed(key);
            boolean wasPressed = keyStates.getOrDefault(key, false);
            if (pressed && !wasPressed) {
                keyStates.put(key, true);
                for (MugenKeyListener listener : mugenKeyListeners) {
                    listener.action(key, true);
                }
            } else if (!pressed && wasPressed) {
                keyStates.put(key, false);
                for (MugenKeyListener listener : mugenKeyListeners) {
                    listener.action(key, false);
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
        if (batch != null) {
            batch.dispose();
        }
        gameRunning = false;
    }
    
    @Override
    public int getKeyEsc() {
        return Input.Keys.ESCAPE;
    }
    
    @Override
    public int getKeyF1() {
        return Input.Keys.F1;
    }
    
    @Override
    public int getKeyF2() {
        return Input.Keys.F2;
    }
    
    @Override
    public int getKeyF3() {
        return Input.Keys.F3;
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
        spriteCmdProcess.clear();
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
    
    public OrthographicCamera getCamera() {
        return camera;
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
}


