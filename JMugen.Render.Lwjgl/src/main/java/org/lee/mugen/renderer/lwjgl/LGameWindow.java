package org.lee.mugen.renderer.lwjgl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.glfw.GLFW.*;

public class LGameWindow implements GameWindow {

    private class DebugEventManager {
        private Map<DebugAction, int[]> actionKeyMap = new HashMap<DebugAction, int[]>();
        private Map<DebugAction, Boolean> actionPressMap = new HashMap<DebugAction, Boolean>();


        public DebugEventManager() {
//			addAction(DebugAction.SWICTH_PLAYER_DEBUG_INFO, new int[] {Keyboard.KEY_LCONTROL, Keyboard.KEY_D});
//			addAction(DebugAction.EXPLOD_DEBUG_INFO, new int[] {Keyboard.KEY_LCONTROL, Keyboard.KEY_E});
//			addAction(DebugAction.INIT_PLAYER, new int[] {Keyboard.KEY_SPACE});
//			addAction(DebugAction.SHOW_HIDE_CNS, new int[] {Keyboard.KEY_LCONTROL, Keyboard.KEY_C});
//			addAction(DebugAction.SHOW_HIDE_ATTACK_CNS, new int[] {Keyboard.KEY_LCONTROL, Keyboard.KEY_X});
//			addAction(DebugAction.INCREASE_FPS, new int[] {Keyboard.KEY_LCONTROL, Keyboard.KEY_ADD}, true);
//			addAction(DebugAction.DECREASE_FPS, new int[] {Keyboard.KEY_LCONTROL, Keyboard.KEY_SUBTRACT}, true);
//			addAction(DebugAction.RESET_FPS, new int[] {Keyboard.KEY_LCONTROL, Keyboard.KEY_MULTIPLY});
//
//			addAction(DebugAction.DEBUG_PAUSE, new int[] {Keyboard.KEY_LCONTROL, Keyboard.KEY_P});
//			addAction(DebugAction.PAUSE_PLUS_ONE_FRAME, new int[] {Keyboard.KEY_LCONTROL, Keyboard.KEY_A});
//
//			addAction(DebugAction.DISPLAY_HELP, new int[] {Keyboard.KEY_F1});


            addAction(DebugAction.SWITCH_PLAYER_DEBUG_INFO, new int[] {GLFW_KEY_LEFT_CONTROL, GLFW_KEY_D});
            addAction(DebugAction.EXPLOD_DEBUG_INFO, new int[] {GLFW_KEY_LEFT_CONTROL, GLFW_KEY_E});
            addAction(DebugAction.INIT_PLAYER, new int[] {GLFW_KEY_SPACE});
            addAction(DebugAction.SHOW_HIDE_CNS, new int[] {GLFW_KEY_LEFT_CONTROL, GLFW_KEY_C});
            addAction(DebugAction.SHOW_HIDE_ATTACK_CNS, new int[] {GLFW_KEY_LEFT_CONTROL, GLFW_KEY_X});
            addAction(DebugAction.INCREASE_FPS, new int[] {GLFW_KEY_LEFT_CONTROL, GLFW_KEY_KP_ADD}, true);
            addAction(DebugAction.DECREASE_FPS, new int[] {GLFW_KEY_LEFT_CONTROL, GLFW_KEY_KP_SUBTRACT}, true);
            addAction(DebugAction.RESET_FPS, new int[] {GLFW_KEY_LEFT_CONTROL, GLFW_KEY_KP_MULTIPLY});

            addAction(DebugAction.DEBUG_PAUSE, new int[] {GLFW_KEY_LEFT_CONTROL, GLFW_KEY_P});
            addAction(DebugAction.PAUSE_PLUS_ONE_FRAME, new int[] {GLFW_KEY_LEFT_CONTROL, GLFW_KEY_A});
            addAction(DebugAction.DISPLAY_HELP, new int[] {GLFW_KEY_F1});
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
            for (DebugAction action: actionKeyMap.keySet()) {
                boolean isAllKeyOk = true;
                for (int key: actionKeyMap.get(action)) {
                    isAllKeyOk = isAllKeyOk && isKeyDown(key);
                }
                if (isAllKeyOk && actionPressMap.get(action) == null) {
                    if (callback instanceof AbstractGameFight) {
                        ((AbstractGameFight)callback).onDebugAction(action);
                    }

                    continue;
                }
                if (isAllKeyOk) { // on Press
                    actionPressMap.put(action, true);
                } else if (actionPressMap.get(action) != null && actionPressMap.get(action)) { // on release
                    actionPressMap.put(action, false);
                    if (callback instanceof AbstractGameFight) {
                        ((AbstractGameFight)callback).onDebugAction(action);
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

    private long window;
    private int framebufferWidth;
    private int framebufferHeight;

    public void addSpriteKeyProcessor(ISpriteCmdProcess scp) {
        spriteCmdProcess.add(new SprCmdProcessListenerAction(scp));
    }

    private void initDisplay() throws Exception {
        try {
            setDisplayMode();
//			Display.create();

            // Set clear color to black before clearing
            GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            
            // Note: Initial viewport is set after setDisplayMode() initializes framebuffer size
            
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            // enable textures since we're going to use these for our sprites
            GL11.glEnable(GL11.GL_TEXTURE_2D);

            // disable the OpenGL depth test since we're rendering 2D graphics
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_BLEND);

            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();

            setTitle("JMugen");
            // grab the mouse, don't want that hideous cursor when we're playing!
            // Mouse.setGrabbed(true);

            GL11.glOrtho(0, width, height, 0, -1, 1);

            GL11.glScaled((float) width / 320, (float) height / 240, 0);

            initKeys();

//			Mouse.create();
            if (myFBOId == 0) {
                IntBuffer buffer = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer(); // allocate a 1 int byte buffer
                EXTFramebufferObject.glGenFramebuffersEXT( buffer ); // generate
                int status = EXTFramebufferObject.glCheckFramebufferStatusEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT);
                if (status != EXTFramebufferObject.GL_FRAMEBUFFER_COMPLETE_EXT) {
                    System.err.println("Framebuffer not complete! Status: " + status);
                }
                myFBOId = buffer.get(0);
                BufferedImage img = new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);
                try {
                    temp = TextureLoader.getTextureLoader().getTexture(img);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

//		} catch (LWJGLException le) {
        } catch (Exception le) {
            Logger.error(le.getMessage());
        }
    }

    int myFBOId = 0;
    Texture temp;

    public void gameLoop() throws Exception {
        callback.init(this);
        int lack = 0;
        while (gameRunning) {
            // Poll for window events - required in LWJGL3 to process system messages and prevent window freezing
            GLFW.glfwPollEvents();
            
            // Update viewport to match current framebuffer size (for Retina/HiDPI displays)
            GL11.glViewport(0, 0, framebufferWidth, framebufferHeight);
            
            ((LMugenTimer) timer).listen();
            if (1f/timer.getFramerate() > timer.getDeltas()) {
                continue;
            }

            if (getTimer().getFramerate() == 0) {
                getTimer().sleep(1000 / 60);
                continue;
            }
            GL11.glEnable(GL11.GL_TEXTURE_2D);

            // disable the OpenGL depth test since we're rendering 2D graphics
            if (mouse.isLeftClick() && !isButtonDown(0)) {
                mouse.setLeftPress(false);
            } else {
                mouse.setLeftPress(true);
            }
            if (!isFinishInit) {
                if (callback instanceof AbstractGameFight) {
                    ((AbstractGameFight)callback).displayPendingScreeen();
                }
            } else {
                if (callback != null) {
                    keyManagementExecute();
                    if (getTimer().getFramerate() == 0) {
                        getTimer().sleep(1000 / 60);
                        callback.render();
//						Display.update();
                        displayUpdate();
                        continue;
                    }

                    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT
                            | GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_ACCUM_BUFFER_BIT);
                    GL11.glMatrixMode(GL11.GL_MODELVIEW);
                    GL11.glLoadIdentity();

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

//			if (Display.isCloseRequested()) {
//				gameRunning = false;
//				Display.destroy();
//				System.exit(0);
//			}

            if(GLFW.glfwWindowShouldClose(window)){
                gameRunning = false;
                GLFW.glfwDestroyWindow(window);
                GLFW.glfwTerminate();
                System.exit(0);
            }

        }

    }

    private boolean isButtonDown(int button) {
        return GLFW.glfwGetMouseButton(window, GLFW.GLFW_MOUSE_BUTTON_1) == GLFW.GLFW_PRESS;
    }

    private void displayUpdate(){
        GLFW.glfwSwapBuffers(window);
    }

    public void render() {

        if (isRender()) {

            EXTFramebufferObject.glBindFramebufferEXT( EXTFramebufferObject.GL_FRAMEBUFFER_EXT, myFBOId );
            EXTFramebufferObject.glFramebufferTexture2DEXT( EXTFramebufferObject.GL_FRAMEBUFFER_EXT, EXTFramebufferObject.GL_COLOR_ATTACHMENT0_EXT,
                    GL11.GL_TEXTURE_2D, temp.getTextureID(), 0);
            EXTFramebufferObject.glBindFramebufferEXT( EXTFramebufferObject.GL_FRAMEBUFFER_EXT, myFBOId );
            GL11.glPushAttrib(GL11.GL_VIEWPORT_BIT);
            GL11.glViewport( 0, 0, 640, 480);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            try {
                callback.render();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            EXTFramebufferObject.glBindFramebufferEXT( EXTFramebufferObject.GL_FRAMEBUFFER_EXT, 0);
            GL11.glPopAttrib();

            temp.bind();

            DrawProperties dp = new DrawProperties(0, 0, false, true, new ImageContainer(temp, 640, 480));


            float scale = 0.5f;
            float alpha = GraphicsWrapper.getInstance().getAlpha();
            GL11.glScalef(scale, scale, 1);
            GL11.glColor4f(alpha, alpha, alpha, alpha);

            GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE,
                    GL11.GL_MODULATE);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);

            ((LMugenDrawer)GraphicsWrapper.getInstance()).drawChild(dp);
            GL11.glScalef(1f/scale, 1f/scale, 1);

            if (callback instanceof AbstractGameFight) {
                ((AbstractGameFight)callback).renderDebugInfo();
            }
        }
//		Display.update();
        displayUpdate();
    }


    public Game getCallback() {
        return callback;
    }

    public int getHeight() {
        return height;
    }

    @Override
//	public MouseCtrl getMouseStatus() {
//		Mouse.next();
//		mouse.setX(Mouse.getX()/2);
//		mouse.setY(240 - Mouse.getY()/2);
//
//		mouse.setLeftPress(Mouse.isButtonDown(0));
//		mouse.setLeftRelease(!Mouse.isButtonDown(0));
//		return mouse;
//	}
    public MouseCtrl getMouseStatus() {
        // Atualiza as informações do mouse
        try (MemoryStack stack = MemoryStack.stackPush()) {
            // Obtendo as coordenadas do cursor do mouse
            double[] xpos = new double[1], ypos = new double[1];
            GLFW.glfwGetCursorPos(window, xpos, ypos);

            // Atualiza as coordenadas do mouse (dividido por 2, conforme seu código original)
            mouse.setX((int) (xpos[0] / 2));
            mouse.setY(240 - (int) (ypos[0] / 2));

            // Obtém o estado do botão esquerdo do mouse (0 para esquerdo)
            boolean leftPress = GLFW.glfwGetMouseButton(window, GLFW.GLFW_MOUSE_BUTTON_1) == GLFW.GLFW_PRESS;
            boolean leftRelease = !leftPress;

            // Atualiza o estado do botão esquerdo do mouse
            mouse.setLeftPress(leftPress);
            mouse.setLeftRelease(leftRelease);
        }

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

    private int getKeyFromBundle(ResourceBundle bundle, String key) {
        // Mapeia a tecla com base na string obtida do ResourceBundle
        String keyName = bundle.getString(key).toUpperCase();

        // Mapeamento manual das teclas
        switch (keyName) {
            case "UP": return GLFW.GLFW_KEY_UP;
            case "DOWN": return GLFW.GLFW_KEY_DOWN;
            case "LEFT": return GLFW.GLFW_KEY_LEFT;
            case "RIGHT": return GLFW.GLFW_KEY_RIGHT;
            case "A": return GLFW.GLFW_KEY_A;
            case "B": return GLFW.GLFW_KEY_B;
            case "C": return GLFW.GLFW_KEY_C;
            case "X": return GLFW.GLFW_KEY_X;
            case "Y": return GLFW.GLFW_KEY_Y;
            case "Z": return GLFW.GLFW_KEY_Z;
            case "ABC": return GLFW.GLFW_KEY_A; // Ajuste conforme necessário
            case "XYZ": return GLFW.GLFW_KEY_X; // Ajuste conforme necessário
            default: return -1; // Caso a tecla não seja encontrada, você pode retornar um valor padrão
        }
    }

    private void initKeys() throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException {
        ResourceBundle bundle = ResourceBundleHelper.getBundle("keys-lwjgl");
        {
            CmdProcessListener cmdProcessListener = new CmdProcessListener();
//			cmdProcessListener.setKeys(new int[] {Keyboard.KEY_ESCAPE, Keyboard.KEY_F1, Keyboard.KEY_F2, Keyboard.KEY_F3, Keyboard.KEY_F4, Keyboard.KEY_F5,Keyboard.KEY_F6, Keyboard.KEY_F7, Keyboard.KEY_F8,Keyboard.KEY_F9, Keyboard.KEY_F10, Keyboard.KEY_F11, Keyboard.KEY_F12});
            cmdProcessListener.setKeys(new int[] {
                    GLFW.GLFW_KEY_ESCAPE, GLFW.GLFW_KEY_F1, GLFW.GLFW_KEY_F2, GLFW.GLFW_KEY_F3, GLFW.GLFW_KEY_F4, GLFW.GLFW_KEY_F5,
                    GLFW.GLFW_KEY_F6, GLFW.GLFW_KEY_F7, GLFW.GLFW_KEY_F8, GLFW.GLFW_KEY_F9, GLFW.GLFW_KEY_F10, GLFW.GLFW_KEY_F11, GLFW.GLFW_KEY_F12
            });
        }
        // P1
//		CmdProcDispatcher cd1 = new CmdProcDispatcher(
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P1.UP").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P1.DOWN").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P1.LEFT").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P1.RIGHT").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P1.A").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P1.B").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P1.C").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P1.X").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P1.Y").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P1.Z").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P1.ABC").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P1.XYZ").toUpperCase()).getInt(null));

        CmdProcDispatcher cd1 = new CmdProcDispatcher(
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P1.UP").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P1.DOWN").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P1.LEFT").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P1.RIGHT").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P1.A").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P1.B").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P1.C").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P1.X").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P1.Y").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P1.Z").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P1.ABC").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P1.XYZ").toUpperCase()).getInt(null));


        CmdProcDispatcher.getSpriteDispatcherMap().put("1", cd1);
        {
            CmdProcessListener cmdProcessListener = new CmdProcessListener();
            cmdProcessListener.setKeys(cd1.getKeys());
            cmdProcess.add(cmdProcessListener);
        }
//		CmdProcDispatcher cd2 = new CmdProcDispatcher(
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P2.UP").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P2.DOWN").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P2.LEFT").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P2.RIGHT").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P2.A").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P2.B").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P2.C").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P2.X").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P2.Y").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P2.Z").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P2.ABC").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P2.XYZ").toUpperCase()).getInt(null));

        CmdProcDispatcher cd2 = new CmdProcDispatcher(
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P2.UP").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P2.DOWN").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P2.LEFT").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P2.RIGHT").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P2.A").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P2.B").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P2.C").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P2.X").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P2.Y").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P2.Z").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P2.ABC").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P2.XYZ").toUpperCase()).getInt(null));
        CmdProcDispatcher.getSpriteDispatcherMap().put("2", cd2);
        {
            CmdProcessListener cmdProcessListener = new CmdProcessListener();
            cmdProcessListener.setKeys(cd2.getKeys());
            cmdProcess.add(cmdProcessListener);
        }
//		CmdProcDispatcher cd3 = new CmdProcDispatcher(
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P3.UP").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P3.DOWN").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P3.LEFT").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P3.RIGHT").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P3.A").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P3.B").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P3.C").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P3.X").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P3.Y").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P3.Z").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P3.ABC").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P3.XYZ").toUpperCase()).getInt(null));
        CmdProcDispatcher cd3 = new CmdProcDispatcher(
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P3.UP").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P3.DOWN").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P3.LEFT").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P3.RIGHT").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P3.A").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P3.B").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P3.C").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P3.X").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P3.Y").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P3.Z").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P3.ABC").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P3.XYZ").toUpperCase()).getInt(null));

        CmdProcDispatcher.getSpriteDispatcherMap().put("3", cd3);
        {
            CmdProcessListener cmdProcessListener = new CmdProcessListener();
            cmdProcessListener.setKeys(cd3.getKeys());
            cmdProcess.add(cmdProcessListener);
        }

//		CmdProcDispatcher cd4 = new CmdProcDispatcher(
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P4.UP").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P4.DOWN").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P4.LEFT").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P4.RIGHT").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P4.A").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P4.B").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P4.C").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P4.X").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P4.Y").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P4.Z").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P4.ABC").toUpperCase()).getInt(null),
//				Keyboard.class.getDeclaredField("KEY_" + bundle.getString("P4.XYZ").toUpperCase()).getInt(null));

        CmdProcDispatcher cd4 = new CmdProcDispatcher(
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P4.UP").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P4.DOWN").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P4.LEFT").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P4.RIGHT").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P4.A").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P4.B").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P4.C").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P4.X").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P4.Y").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P4.Z").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P4.ABC").toUpperCase()).getInt(null),
                GLFW.class.getDeclaredField("GLFW_KEY_" + bundle.getString("P4.XYZ").toUpperCase()).getInt(null));

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

    private boolean isKeyDown(int key) {
        return glfwGetKey(window, key) == GLFW_PRESS;
    }

    private void keyManagementExecute() {
        debugEventManager.process(callback);

        for (CmdProcessListener cmd: cmdProcess) {
            boolean[] areKeysPress = cmd.getAreKeysPress();
            int[] keys = cmd.getKeys();

            for (int i = 0; i < keys.length; ++i) {
                if (!areKeysPress[i] && isKeyDown(keys[i])) {
                    areKeysPress[i] = true;
                    for (MugenKeyListener kl: mugenKeyListeners)
                        kl.action(keys[i], true);

                } else if (areKeysPress[i] && !isKeyDown(keys[i])) {
                    areKeysPress[i] = false;
                    for (MugenKeyListener kl: mugenKeyListeners)
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
                    for (MugenKeyListener kl: mugenKeyListeners)
                        kl.action(keys[i], true);

                } else if (areKeysPress[i] && !isKeyDown(keys[i])) {
                    areKeysPress[i] = false;
                    scp.keyReleased(keys[i]);
                    for (MugenKeyListener kl: mugenKeyListeners)
                        kl.action(keys[i], false);
                }

            }
        }
    }

//	private void keyManagementExecute2() {
//        while (Keyboard.next() )  {
//            // pass key event to handler
//            if (Keyboard.getEventKeyState()) {
//                keyDown(Keyboard.getEventKey());
//            }
//            else {
//                keyUp(Keyboard.getEventKey());
//            }
//        }
//	}

    private void keyUp(int eventKey) {
        for (SprCmdProcessListenerAction sa : spriteCmdProcess) {
            ISpriteCmdProcess scp = sa.getScp();
            scp.keyReleased(eventKey);
        }

    }
    @Override
    public void removeSpriteKeysProcessors() {
        spriteCmdProcess.clear();
    }
    public void removeSpriteKeyProcessor(ISpriteCmdProcess scp) {
        for (Iterator<SprCmdProcessListenerAction> iter = spriteCmdProcess
                .iterator(); iter.hasNext();) {
            SprCmdProcessListenerAction sa = iter.next();
            if (sa.getScp().equals(scp))
                iter.remove();
        }
    }

    public void setCallback(Game callback) {
        this.callback = callback;
    }

//	private boolean setDisplayMode() {
//		try {
//			// get modes
//			DisplayMode[] dm = org.lwjgl.util.Display.getAvailableDisplayModes(
//					width, height, -1, -1, -1, -1, 60, 60);
//
//			org.lwjgl.util.Display.setDisplayMode(dm, new String[] {
//					"width=" + width,
//					"height=" + height,
//					"freq=" + 60,
//					"bpp="
//							+ org.lwjgl.opengl.Display.getDisplayMode()
//									.getBitsPerPixel() });
//
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out
//					.println("Unable to enter fullscreen, continuing in windowed mode");
//		}
//
//		return false;
//	}

    private boolean setDisplayMode() {
        try {
            // Set error callback before initializing GLFW
            GLFW.glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));

            if (!GLFW.glfwInit()) {
                throw new IllegalStateException("Unable to initialize GLFW");
            }

            System.out.println("GLFW initialized successfully");

            // Set window hints before creating the window
            GLFW.glfwDefaultWindowHints();
            GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE); // Hide during setup to prevent flickering
            GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
            GLFW.glfwWindowHint(GLFW.GLFW_REFRESH_RATE, 60);  // Taxa de atualização
            GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES, 4); // Anti-aliasing (opcional)

            window = GLFW.glfwCreateWindow(width, height, "JMugen", 0, 0);
            if (window == 0) {
                System.out.println("Failed to create the GLFW window.");
                return false;
            }

            // Center the window on screen
            GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
            if (vidmode != null) {
                GLFW.glfwSetWindowPos(window, 
                    (vidmode.width() - width) / 2, 
                    (vidmode.height() - height) / 2);
            }

            // Tornar a janela o contexto atual
            GLFW.glfwMakeContextCurrent(window);
            GL.createCapabilities(); // Habilita OpenGL no contexto
            GLFW.glfwSwapInterval(1); // Sincronizar com a taxa de atualização do monitor
            
            // Get actual framebuffer size (important for Retina/HiDPI displays)
            int[] fbWidth = new int[1];
            int[] fbHeight = new int[1];
            GLFW.glfwGetFramebufferSize(window, fbWidth, fbHeight);
            framebufferWidth = fbWidth[0];
            framebufferHeight = fbHeight[0];
            Logger.log("Window size: " + width + "x" + height + ", Framebuffer size: " + framebufferWidth + "x" + framebufferHeight);
            
            // Set initial viewport to match framebuffer size
            GL11.glViewport(0, 0, framebufferWidth, framebufferHeight);
            
            // Set framebuffer size callback to handle Retina display changes
            GLFW.glfwSetFramebufferSizeCallback(window, (win, w, h) -> {
                framebufferWidth = w;
                framebufferHeight = h;
                GL11.glViewport(0, 0, w, h);
                Logger.log("Framebuffer resized to: " + w + "x" + h);
            });
            
            // Show the window after everything is set up
            GLFW.glfwShowWindow(window);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to enter fullscreen, continuing in windowed mode");
        }

        return false;
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

    public void setResolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setTitle(String title) {
        this.title = title;
//		if (Display.isCreated()) {
//			Display.setTitle(title);
//		}
        if (window != 0) {
            GLFW.glfwSetWindowTitle(window, title);
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
        return GLFW.GLFW_KEY_ESCAPE;
    }

    @Override
    public int getKeyF1() {
        return GLFW.GLFW_KEY_F1;
    }

    @Override
    public int getKeyF2() {
        return GLFW.GLFW_KEY_F2;
    }

    @Override
    public int getKeyF3() {
        return GLFW.GLFW_KEY_F3;
    }


}
