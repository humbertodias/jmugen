package org.lee.mugen.renderer.libgdx.android;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.lee.mugen.core.Game;
import org.lee.mugen.input.ISpriteCmdProcess;
import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.renderer.MugenTimer;
import org.lee.mugen.renderer.libgdx.core.GDXKeyMapper;
import org.lee.mugen.renderer.libgdx.core.LGDXImageLoader;
import org.lee.mugen.renderer.libgdx.core.LGDXMugenTimer;
import org.lee.mugen.renderer.libgdx.core.LGDXPalFxShader;
import org.lee.mugen.renderer.libgdx.core.LGDXRenderContext;

/**
 * Android LibGDX game window: same render/update loop as {@code LGDXWebGameWindow} without GWT hooks.
 */
public class LGDXAndroidGameWindow implements GameWindow, ApplicationListener, LGDXRenderContext {

    private static class SprCmdProcessListenerAction {
        private final ISpriteCmdProcess scp;
        private final int[] keys;
        private final boolean[] areKeysPress;

        SprCmdProcessListenerAction(ISpriteCmdProcess scp) {
            this.scp = scp;
            this.keys = scp.getKeys();
            this.areKeysPress = new boolean[keys.length];
        }
    }

    private int width;
    private int height;
    private String title;
    private Game callback;

    private final LGDXMugenTimer timer = new LGDXMugenTimer();
    private final LGDXAndroidImageLoader imageLoader = new LGDXAndroidImageLoader();
    private final LGDXAndroidPalFxShader palFxShader = new LGDXAndroidPalFxShader();
    private final GameWindow.MouseCtrl mouse = new GameWindow.MouseCtrl();
    private final Vector3 unprojectTmp = new Vector3();
    private final Matrix4 batchTransformIdentity = new Matrix4();
    private final Matrix4 worldProjectionSnapshot = new Matrix4();
    private final List<SprCmdProcessListenerAction> spriteCmdProcess = new LinkedList<>();
    private final List<MugenKeyListener> mugenKeyListeners = new ArrayList<>();

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private boolean finishInit;
    private boolean gameRunning = true;

    public LGDXAndroidGameWindow() {
        setTitle("JMugen - LibGDX Android");
        setResolution(640, 480);
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
    public void start() {
        // Android drives the loop via {@link com.badlogic.gdx.backends.android.AndroidApplication}.
    }

    @Override
    public void setGameWindowCallback(Game callback) {
        this.callback = callback;
    }

    @Override
    public void addSpriteKeyProcessor(ISpriteCmdProcess scp) {
        if (scp != null) {
            spriteCmdProcess.add(new SprCmdProcessListenerAction(scp));
        }
    }

    @Override
    public MouseCtrl getMouseStatus() {
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
        boolean right = Gdx.input.isButtonPressed(Input.Buttons.RIGHT);
        mouse.setLeftPress(left);
        mouse.setLeftRelease(!left);
        mouse.setRightPress(right);
        mouse.setRightRelease(!right);
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

        if (callback == null) {
            finishInit = true;
            return;
        }

        try {
            callback.init(this);
            finishInit = true;
        } catch (Exception e) {
            Gdx.app.error("JMugenAndroid", "Error initializing Android game", e);
            throw new RuntimeException("Failed to initialize Android game callback", e);
        }
    }

    @Override
    public void resize(int width, int height) {
        if (viewport == null || camera == null || batch == null) {
            return;
        }
        viewport.update(width, height, true);
        camera.setToOrtho(true, 320, 240);
        batch.setProjectionMatrix(camera.combined);
        worldProjectionSnapshot.set(camera.combined);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (viewport == null || batch == null || callback == null || !finishInit || !gameRunning) {
            return;
        }

        keyManagementExecute();
        viewport.apply();

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
            } finally {
                if (batch.isDrawing()) {
                    batch.end();
                }
            }
        } catch (Exception e) {
            Gdx.app.error("JMugenAndroid", "Error updating Android game", e);
        }
    }

    private void keyManagementExecute() {
        for (SprCmdProcessListenerAction action : spriteCmdProcess) {
            for (int i = 0; i < action.keys.length; ++i) {
                int key = action.keys[i];
                if (!action.areKeysPress[i] && Gdx.input.isKeyPressed(key)) {
                    action.areKeysPress[i] = true;
                    action.scp.keyPressed(key);
                    notifyKeyListeners(key, true);
                } else if (action.areKeysPress[i] && !Gdx.input.isKeyPressed(key)) {
                    action.areKeysPress[i] = false;
                    action.scp.keyReleased(key);
                    notifyKeyListeners(key, false);
                }
            }
        }
    }

    private void notifyKeyListeners(int key, boolean isPress) {
        for (MugenKeyListener listener : mugenKeyListeners) {
            listener.action(key, isPress);
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
            batch = null;
        }
        if (callback != null) {
            callback.free();
            callback = null;
        }
        gameRunning = false;
    }

    @Override
    public int getKeyEsc() {
        return GDXKeyMapper.KEY_ESCAPE;
    }

    @Override
    public int getKeyF1() {
        return GDXKeyMapper.KEY_F1;
    }

    @Override
    public int getKeyF2() {
        return GDXKeyMapper.KEY_F2;
    }

    @Override
    public int getKeyF3() {
        return GDXKeyMapper.KEY_F3;
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
    public SpriteBatch getBatch() {
        return batch;
    }

    @Override
    public Matrix4 getWorldProjectionSnapshot() {
        return worldProjectionSnapshot;
    }

    @Override
    public OrthographicCamera getCamera() {
        return camera;
    }

    @Override
    public Viewport getViewport() {
        return viewport;
    }

    @Override
    public int getGameWidth() {
        return width;
    }

    @Override
    public int getGameHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public boolean isFinishInit() {
        return finishInit;
    }
}
