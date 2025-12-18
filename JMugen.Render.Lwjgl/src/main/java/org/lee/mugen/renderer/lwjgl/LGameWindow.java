package org.lee.mugen.renderer.lwjgl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.lee.mugen.ResourceBundleHelper;
import org.lee.mugen.core.AbstractGameFight;
import org.lee.mugen.core.Game;
import org.lee.mugen.core.AbstractGameFight.DebugAction;
import org.lee.mugen.input.CmdProcDispatcher;
import org.lee.mugen.input.ISpriteCmdProcess;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.MugenTimer;
import org.lee.mugen.util.Logger;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class LGameWindow implements GameWindow {

    private class DebugEventManager {
        private Map<DebugAction, int[]> actionKeyMap = new HashMap<DebugAction, int[]>();
        private Map<DebugAction, Boolean> actionPressMap = new HashMap<DebugAction, Boolean>();


        public DebugEventManager() {
            addAction(DebugAction.SWITCH_PLAYER_DEBUG_INFO, new int[]{GLFWKeyMapper.KEY_LCONTROL, GLFWKeyMapper.KEY_D});
            addAction(DebugAction.EXPLOD_DEBUG_INFO, new int[]{GLFWKeyMapper.KEY_LCONTROL, GLFWKeyMapper.KEY_E});
            addAction(DebugAction.INIT_PLAYER, new int[]{GLFWKeyMapper.KEY_SPACE});
            addAction(DebugAction.SHOW_HIDE_CNS, new int[]{GLFWKeyMapper.KEY_LCONTROL, GLFWKeyMapper.KEY_C});
            addAction(DebugAction.SHOW_HIDE_ATTACK_CNS, new int[]{GLFWKeyMapper.KEY_LCONTROL, GLFWKeyMapper.KEY_X});
            addAction(DebugAction.INCREASE_FPS, new int[]{GLFWKeyMapper.KEY_LCONTROL, GLFWKeyMapper.KEY_ADD}, true);
            addAction(DebugAction.DECREASE_FPS, new int[]{GLFWKeyMapper.KEY_LCONTROL, GLFWKeyMapper.KEY_SUBTRACT}, true);
            addAction(DebugAction.RESET_FPS, new int[]{GLFWKeyMapper.KEY_LCONTROL, GLFWKeyMapper.KEY_MULTIPLY});

            addAction(DebugAction.DEBUG_PAUSE, new int[]{GLFWKeyMapper.KEY_LCONTROL, GLFWKeyMapper.KEY_P});
            addAction(DebugAction.PAUSE_PLUS_ONE_FRAME, new int[]{GLFWKeyMapper.KEY_LCONTROL, GLFWKeyMapper.KEY_A});

            addAction(DebugAction.DISPLAY_HELP, new int[]{GLFWKeyMapper.KEY_F1});


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
                    isAllKeyOk = isAllKeyOk && isKeyDown(key);
                }
                if (isAllKeyOk && actionPressMap.get(action) == null) {
                    if (callback instanceof AbstractGameFight) {
                        ((AbstractGameFight) callback).onDebugAction(action);
                    }

                    continue;
                }
                if (isAllKeyOk) { // on Press
                    actionPressMap.put(action, true);
                } else if (actionPressMap.get(action) != null && actionPressMap.get(action)) { // on release
                    actionPressMap.put(action, false);
                    if (callback instanceof AbstractGameFight) {
                        ((AbstractGameFight) callback).onDebugAction(action);
                    }
                }
            }

        }

    }

    private static class CmdProcessListener {
        boolean[] areKeysPress;
        int[] keys;

        public boolean[] getAreKeysPress() {
            return areKeysPress;
        }

        public int[] getKeys() {
            return keys;
        }

        public void setAreKeysPress(boolean[] areKeysPress) {
            this.areKeysPress = areKeysPress;
        }

        public void setKeys(int[] keys) {
            this.keys = keys;
            areKeysPress = new boolean[keys.length];
        }
    }

    private static class SprCmdProcessListenerAction extends CmdProcessListener {

        private ISpriteCmdProcess scp;

        public SprCmdProcessListenerAction(ISpriteCmdProcess scp) {
            this.scp = scp;
            keys = scp.getKeys();
            areKeysPress = new boolean[keys.length];
        }

        public ISpriteCmdProcess getScp() {
            return scp;
        }

    }

    public LGameWindow() {
        setResolution(640, 480);
    }

    private Game callback;

    private DebugEventManager debugEventManager = new DebugEventManager();

    private boolean gameRunning = true;
    private int width;
    private int height;
    boolean isFinishInit = true;
    boolean isLeftRelease = false;

    MouseCtrl mouse = new MouseCtrl();

    private List<CmdProcessListener> cmdProcess = new LinkedList<CmdProcessListener>();
    private List<SprCmdProcessListenerAction> spriteCmdProcess = new LinkedList<SprCmdProcessListenerAction>();

    private LMugenTimer timer = new LMugenTimer();

    /**
     * Title of window, we get it before our window is ready, so store it till
     * needed
     */
    private String title;

    // GLFW window handle
    private long window;
    
    // Keyboard state tracking for GLFW
    private Map<Integer, Boolean> keyStates = new HashMap<>();
    
    // Mouse state tracking
    private double mouseX = 0;
    private double mouseY = 0;
    private boolean leftButtonPressed = false;


    public void addSpriteKeyProcessor(ISpriteCmdProcess scp) {
        spriteCmdProcess.add(new SprCmdProcessListenerAction(scp));
    }
    
    // Helper method to check if a key is currently pressed
    private boolean isKeyDown(int key) {
        return keyStates.getOrDefault(key, false);
    }

    private void initDisplay() throws Exception {
        // Initialize GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        
        // macOS requires forward-compatible context for OpenGL 3.0+
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 2);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1);

        // Create the window
        window = glfwCreateWindow(width, height, "JMugen - LWJGL 3", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Setup keyboard callback
        glfwSetKeyCallback(window, (win, key, scancode, action, mods) -> {
            if (action == GLFW_PRESS) {
                keyStates.put(key, true);
            } else if (action == GLFW_RELEASE) {
                keyStates.put(key, false);
            }
        });

        // Setup mouse callbacks
        glfwSetCursorPosCallback(window, (win, xpos, ypos) -> {
            mouseX = xpos;
            mouseY = ypos;
        });

        glfwSetMouseButtonCallback(window, (win, button, action, mods) -> {
            if (button == GLFW_MOUSE_BUTTON_LEFT) {
                leftButtonPressed = (action == GLFW_PRESS);
            }
        });

        // Get the thread stack and push a new frame
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                window,
                (vidmode.width() - pWidth.get(0)) / 2,
                (vidmode.height() - pHeight.get(0)) / 2
            );
        }

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        setTitle("JMugen - LWJGL 3");

        // Set the clear color (background) to black
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        // enable textures since we're going to use these for our sprites
        glEnable(GL_TEXTURE_2D);

        // disable the OpenGL depth test since we're rendering 2D graphics
        glDisable(GL_DEPTH_TEST);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        glOrtho(0, width, height, 0, -1, 1);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        glScaled((float) width / 320, (float) height / 240, 0);
        initKeys();

        if (myFBOId == 0) {
            IntBuffer buffer = ByteBuffer.allocateDirect(1 * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
            EXTFramebufferObject.glGenFramebuffersEXT(buffer);
            myFBOId = buffer.get();
            BufferedImage img = new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);
            try {
                temp = TextureLoader.getTextureLoader().getTexture(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    int myFBOId = 0;
    Texture temp;

    public void gameLoop() throws Exception {
        callback.init(this);
        int lack = 0;
        while (gameRunning && !glfwWindowShouldClose(window)) {
            // Poll for window events
            glfwPollEvents();
            
            timer.listen();
            if (1f / timer.getFramerate() > timer.getDeltas()) {
                continue;
            }

            if (getTimer().getFramerate() == 0) {
                getTimer().sleep(1000 / 60);
                continue;
            }
            glEnable(GL_TEXTURE_2D);

            // disable the OpenGL depth test since we're rendering 2D graphics
            if (mouse.isLeftClick() && !leftButtonPressed) {
                mouse.setLeftPress(false);
            } else {
                mouse.setLeftPress(true);
            }
            if (!isFinishInit) {
                if (callback instanceof AbstractGameFight) {
                    ((AbstractGameFight) callback).displayPendingScreeen();
                }
            } else {
                if (callback != null) {
                    keyManagementExecute();
                    if (getTimer().getFramerate() == 0) {
                        getTimer().sleep(1000 / 60);
                        callback.render();
                        glfwSwapBuffers(window);
                        continue;
                    }

                    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_ACCUM_BUFFER_BIT);
                    glMatrixMode(GL_MODELVIEW);
                    glLoadIdentity();


                    callback.update(1);

                    Game another = callback.getNext();
                    if (another != callback) {
                        another.init(this);
                        callback.free();
                        callback = another;
                    } else {
                        render();
                    }
                }
                lack = getTimer().sleep();
            }

        }
        
        // Cleanup
        glfwDestroyWindow(window);
        glfwTerminate();
        System.exit(0);

    }

    public void render() {

        if (isRender()) {

            EXTFramebufferObject.glBindFramebufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, myFBOId);
            EXTFramebufferObject.glFramebufferTexture2DEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, EXTFramebufferObject.GL_COLOR_ATTACHMENT0_EXT,
                    GL_TEXTURE_2D, temp.getTextureID(), 0);
            EXTFramebufferObject.glBindFramebufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, myFBOId);
            glPushAttrib(GL_VIEWPORT_BIT);
//            glViewport(0, 0, 640, 480);
            glViewport(0, 0, width, height);
            glClear(GL_COLOR_BUFFER_BIT);

            try {
                callback.render();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            EXTFramebufferObject.glBindFramebufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, 0);
            glPopAttrib();

            temp.bind();

            DrawProperties dp = new DrawProperties(0, 0, false, true, new ImageContainer(temp, 640, 480));


            float scale = 0.5f;
            float alpha = GraphicsWrapper.getInstance().getAlpha();
            glScalef(scale, scale, 1);
            glColor4f(alpha, alpha, alpha, alpha);

            glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE,
                    GL_MODULATE);
            glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);

            ((LMugenDrawer) GraphicsWrapper.getInstance()).drawChild(dp);
            glScalef(1f / scale, 1f / scale, 1);


            if (callback instanceof AbstractGameFight) {
                ((AbstractGameFight) callback).renderDebugInfo();
            }
        }
        glfwSwapBuffers(window);
    }


    public Game getCallback() {
        return callback;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public MouseCtrl getMouseStatus() {
        mouse.setX((int)(mouseX / 2));
        mouse.setY((int)(240 - mouseY / 2));

        mouse.setLeftPress(leftButtonPressed);
        mouse.setLeftRelease(!leftButtonPressed);
        return mouse;
    }

    public MugenTimer getTimer() {
        return timer;
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }


    private void initKeys() throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException {
        ResourceBundle bundle = ResourceBundleHelper.getBundle("keys");
        {
            CmdProcessListener cmdProcessListener = new CmdProcessListener();
            cmdProcessListener.setKeys(new int[]{GLFWKeyMapper.KEY_ESCAPE, GLFWKeyMapper.KEY_F1, GLFWKeyMapper.KEY_F2, GLFWKeyMapper.KEY_F3, GLFWKeyMapper.KEY_F4, GLFWKeyMapper.KEY_F5, GLFWKeyMapper.KEY_F6, GLFWKeyMapper.KEY_F7, GLFWKeyMapper.KEY_F8, GLFWKeyMapper.KEY_F9, GLFWKeyMapper.KEY_F10, GLFWKeyMapper.KEY_F11, GLFWKeyMapper.KEY_F12});
            cmdProcess.add(cmdProcessListener);
        }
        // P1


        CmdProcDispatcher cd1 = new CmdProcDispatcher(
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P1.UP").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P1.DOWN").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P1.LEFT").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P1.RIGHT").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P1.A").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P1.B").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P1.C").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P1.X").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P1.Y").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P1.Z").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P1.ABC").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P1.XYZ").toUpperCase()).getInt(null));

        CmdProcDispatcher.getSpriteDispatcherMap().put("1", cd1);
        {
            CmdProcessListener cmdProcessListener = new CmdProcessListener();
            cmdProcessListener.setKeys(cd1.getKeys());
            cmdProcess.add(cmdProcessListener);
        }
        CmdProcDispatcher cd2 = new CmdProcDispatcher(
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P2.UP").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P2.DOWN").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P2.LEFT").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P2.RIGHT").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P2.A").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P2.B").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P2.C").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P2.X").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P2.Y").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P2.Z").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P2.ABC").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P2.XYZ").toUpperCase()).getInt(null));

        CmdProcDispatcher.getSpriteDispatcherMap().put("2", cd2);
        {
            CmdProcessListener cmdProcessListener = new CmdProcessListener();
            cmdProcessListener.setKeys(cd2.getKeys());
            cmdProcess.add(cmdProcessListener);
        }
        CmdProcDispatcher cd3 = new CmdProcDispatcher(
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P3.UP").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P3.DOWN").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P3.LEFT").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P3.RIGHT").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P3.A").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P3.B").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P3.C").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P3.X").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P3.Y").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P3.Z").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P3.ABC").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P3.XYZ").toUpperCase()).getInt(null));

        CmdProcDispatcher.getSpriteDispatcherMap().put("3", cd3);
        {
            CmdProcessListener cmdProcessListener = new CmdProcessListener();
            cmdProcessListener.setKeys(cd3.getKeys());
            cmdProcess.add(cmdProcessListener);
        }

        CmdProcDispatcher cd4 = new CmdProcDispatcher(
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P4.UP").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P4.DOWN").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P4.LEFT").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P4.RIGHT").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P4.A").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P4.B").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P4.C").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P4.X").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P4.Y").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P4.Z").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P4.ABC").toUpperCase()).getInt(null),
                GLFWKeyMapper.class.getDeclaredField("KEY_" + bundle.getString("P4.XYZ").toUpperCase()).getInt(null));

        CmdProcDispatcher.getSpriteDispatcherMap().put("4", cd4);
        {
            CmdProcessListener cmdProcessListener = new CmdProcessListener();
            cmdProcessListener.setKeys(cd4.getKeys());
            cmdProcess.add(cmdProcessListener);
        }

    }

    @Override
    public boolean isRender() {
        return true;
    }

    private void keyDown(int eventKey) {
        for (SprCmdProcessListenerAction sa : spriteCmdProcess) {
            ISpriteCmdProcess scp = sa.getScp();
            scp.keyPressed(eventKey);
        }

    }

    List<MugenKeyListener> mugenKeyListeners = new ArrayList<MugenKeyListener>();

    public void addMugenKeyListener(MugenKeyListener key) {
        mugenKeyListeners.add(key);

    }

    public void clearMugenKeyListener() {
        mugenKeyListeners.clear();

    }

    private void keyManagementExecute() {
        debugEventManager.process(callback);

        for (CmdProcessListener cmd : cmdProcess) {
            boolean[] areKeysPress = cmd.getAreKeysPress();
            int[] keys = cmd.getKeys();

            for (int i = 0; i < keys.length; ++i) {
                if (!areKeysPress[i] && isKeyDown(keys[i])) {
                    areKeysPress[i] = true;
                    for (MugenKeyListener kl : mugenKeyListeners)
                        kl.action(keys[i], true);

                } else if (areKeysPress[i] && !isKeyDown(keys[i])) {
                    areKeysPress[i] = false;
                    for (MugenKeyListener kl : mugenKeyListeners)
                        kl.action(keys[i], false);
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
                    for (MugenKeyListener kl : mugenKeyListeners)
                        kl.action(keys[i], true);

                } else if (areKeysPress[i] && !isKeyDown(keys[i])) {
                    areKeysPress[i] = false;
                    scp.keyReleased(keys[i]);
                    for (MugenKeyListener kl : mugenKeyListeners)
                        kl.action(keys[i], false);
                }

            }
        }
    }

    @Override
    public void removeSpriteKeysProcessors() {
        spriteCmdProcess.clear();
    }

    private boolean setDisplayMode() {
        // This method is now handled in initDisplay() with GLFW
        // Keep it for compatibility but make it a no-op
        return true;
    }


    public void setGameWindowCallback(Game callback) {
        this.callback = callback;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setRender(boolean v) {
        // TODO Auto-generated method stub

    }

    public void setResolution(int x, int y) {
        width = x;
        height = y;
    }

    public void setTitle(String title) {
        this.title = title;
        if (window != 0) {
            glfwSetWindowTitle(window, title);
        }
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void start() throws Exception {
        initDisplay();
        gameLoop();
    }

    @Override
    public void addActionListener(final MugenKeyListener key) {
        addMugenKeyListener(key);
    }

    @Override
    public void clearListener() {
        clearMugenKeyListener();
    }

    @Override
    public int getKeyEsc() {
        return GLFWKeyMapper.KEY_ESCAPE;
    }

    @Override
    public int getKeyF1() {
        return GLFWKeyMapper.KEY_F1;
    }

    @Override
    public int getKeyF2() {
        return GLFWKeyMapper.KEY_F2;
    }

    @Override
    public int getKeyF3() {
        return GLFWKeyMapper.KEY_F3;
    }

}
