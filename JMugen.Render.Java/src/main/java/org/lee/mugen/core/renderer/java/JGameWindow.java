package org.lee.mugen.core.renderer.java;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.lee.framework.swing.WindowsUtils;
import org.lee.mugen.ResourceBundleHelper;
import org.lee.mugen.core.AbstractGameFight;
import org.lee.mugen.core.Game;
import org.lee.mugen.core.AbstractGameFight.DebugAction;
import org.lee.mugen.imageIO.ImageScale2x;
import org.lee.mugen.input.CmdProcDispatcher;
import org.lee.mugen.input.ISpriteCmdProcess;
import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.renderer.MugenTimer;

public class JGameWindow extends Canvas implements GameWindow {

	
	/** The strategy that allows us to use accelerate page flipping */
	protected BufferStrategy strategy;
	/** True if the game is currently "running", i.e. the game loop is looping */
	protected boolean gameRunning = true;
//	private boolean windowFocus = true;
	
	/** The frame in which we'll display our canvas */
	protected JFrame frame;
	/** The width of the display */
	protected int width;
	/** The height of the display */
	protected int height;
	/** The callback which should be notified of events caused by this window */
	protected Game callback;
	/** The current accelerated graphics context */
	protected Graphics2D gStrategy;
	
	protected BufferedImage normalBuffer;
	protected ImageScale2x imgScale2x;
	
//	protected WaterEffect dampingEffect;
	
	public JGameWindow() {
		frame = new JFrame("JMugen - AWT");
		setResolution(640, 480);
		WindowsUtils.centerScreen(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void setTitle(String title) {
		frame.setTitle(title);		
	}
	public void setResolution(int width, int height) {
		this.width = width;
		this.height = height;
		
	}
	private void initKeys() throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException {
		ResourceBundle bundle = ResourceBundleHelper.getBundle("keys");
		
		// P1
		String prefix = "VK_";
		
		CmdProcDispatcher cd1 = new CmdProcDispatcher(
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P1.UP").toUpperCase()).getInt(null), 
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P1.DOWN").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P1.LEFT").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P1.RIGHT").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P1.A").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P1.B").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P1.C").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P1.X").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P1.Y").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P1.Z").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P1.ABC").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P1.XYZ").toUpperCase()).getInt(null));	
		
		
		CmdProcDispatcher.getSpriteDispatcherMap().put("1", cd1);
		
		
		CmdProcDispatcher cd2 = new CmdProcDispatcher(
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P2.UP").toUpperCase()).getInt(null), 
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P2.DOWN").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P2.LEFT").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P2.RIGHT").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P2.A").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P2.B").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P2.C").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P2.X").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P2.Y").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P2.Z").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P2.ABC").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P2.XYZ").toUpperCase()).getInt(null));	
		
		CmdProcDispatcher.getSpriteDispatcherMap().put("2", cd2);
		
		CmdProcDispatcher cd3 = new CmdProcDispatcher(
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P3.UP").toUpperCase()).getInt(null), 
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P3.DOWN").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P3.LEFT").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P3.RIGHT").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P3.A").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P3.B").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P3.C").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P3.X").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P3.Y").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P3.Z").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P3.ABC").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P3.XYZ").toUpperCase()).getInt(null));	
		
		
		CmdProcDispatcher.getSpriteDispatcherMap().put("3", cd3);
		
		
		CmdProcDispatcher cd4 = new CmdProcDispatcher(
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P4.UP").toUpperCase()).getInt(null), 
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P4.DOWN").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P4.LEFT").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P4.RIGHT").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P4.A").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P4.B").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P4.C").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P4.X").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P4.Y").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P4.Z").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P4.ABC").toUpperCase()).getInt(null),
				KeyEvent.class.getDeclaredField(prefix + bundle.getString("P4.XYZ").toUpperCase()).getInt(null));	
		CmdProcDispatcher.getSpriteDispatcherMap().put("4", cd4);
	}
	
	private Map<Integer, Boolean> keyMapPress = new HashMap<Integer, Boolean>();
	private boolean isKeyPress(int key) {
		return keyMapPress.get(key) == null? false: keyMapPress.get(key);
	}
	public void addSpriteKeyProcessor(final ISpriteCmdProcess scp) {
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				scp.keyPressed(e.getKeyCode());
				keyMapPress.put(e.getKeyCode(), true);
				
			}

			@Override
			public void keyReleased(KeyEvent e) {

				scp.keyReleased(e.getKeyCode());
				keyMapPress.put(e.getKeyCode(), false);
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}});
	}

	public void setGameWindowCallback(Game callback) {
		this.callback = callback;		
	}

	public void start() throws Exception  {
		
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(width, height));
		panel.setLayout(null);

		setBounds(0, 0, width, height);
		panel.add(this);
		
		setIgnoreRepaint(true);
		
		// finally make the window visible 
		frame.pack();
		frame.setResizable(false);
		WindowsUtils.centerScreen(frame);
		frame.setVisible(true);
		try {
			initKeys();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		requestFocus();
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		if (callback != null) {
			callback.init(this);
		}

		normalBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		imgScale2x = new ImageScale2x(normalBuffer);


		gameLoop();
	}

	public Graphics2D getDrawGraphics() {
		Graphics2D g = (Graphics2D) normalBuffer.createGraphics();
		g.scale(width/320, height/240);
		return g;
	}
	
	public Graphics2D getDebugDrawGraphics() {
		return gStrategy;
	}
	int lack = 0;
	/**
	 * Run the main game loop. This method keeps rendering the scene
	 * and requesting that the callback update its screen.
	 * @throws Exception 
	 */
	protected void gameLoop() throws Exception {
		
		while (gameRunning) {
			if (getTimer().getFramerate() == 0) {
				getTimer().sleep(1000 / 60);
				continue;
			}
			
			// Get hold of a graphics context for the accelerated 
			// surface and blank it out
			gStrategy = (Graphics2D) strategy.getDrawGraphics();
			gStrategy.setColor(Color.BLACK);
			gStrategy.fillRect(0, 0, width, height);

			if (callback != null) {
				callback.update(1);
//				if (lack-- > 0)
//					continue;
//				synchronized (getTimer()) {
					lack = getTimer().sleep();
					
//				}

				Graphics2D g = (Graphics2D) getDrawGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, 320, 240);
				if (isRender()) {
					callback.render();
					if (callback instanceof AbstractGameFight) {
						AbstractGameFight game = (AbstractGameFight) callback;
						game.renderDebugInfo();
					}
				}
				Image image = normalBuffer;
//				image = imgScale2x.getScaledImage();
//				gStrategy.scale(2, 2);
				gStrategy.drawImage(image, 0, 0, null);


			}

			// finally, we've completed drawing so clear up the graphics
			// and flip the buffer over
			gStrategy.dispose();
			strategy.show();
		}
	}
	public BufferedImage getNormalBuffer() {
		return normalBuffer;
	}
	
	private MugenTimer mugenTimer = new JMugenTimer();

	public MugenTimer getTimer() {
		return mugenTimer;
	}

	
	
	private DebugEventManager debugEventManager = new DebugEventManager();
	
	private class DebugEventManager implements KeyListener {
		private Map<DebugAction, int[]> actionKeyMap = new HashMap<DebugAction, int[]>();
		private Map<DebugAction, Boolean> actionCtrl = new HashMap<DebugAction, Boolean>();
		
		public DebugEventManager() {
			addAction(DebugAction.SWITCH_PLAYER_DEBUG_INFO, new int[] {KeyEvent.VK_D}, true);
			addAction(DebugAction.EXPLOD_DEBUG_INFO, new int[] {KeyEvent.VK_E}, true);
			addAction(DebugAction.INIT_PLAYER, new int[] {KeyEvent.VK_SPACE});
			addAction(DebugAction.SHOW_HIDE_CNS, new int[] {KeyEvent.VK_C}, true);
			addAction(DebugAction.SHOW_HIDE_ATTACK_CNS, new int[] {KeyEvent.VK_X}, true);
			addAction(DebugAction.INCREASE_FPS, new int[] {KeyEvent.VK_ADD}, true);
			addAction(DebugAction.DECREASE_FPS, new int[] {KeyEvent.VK_SUBTRACT}, true);
			addAction(DebugAction.RESET_FPS, new int[] {KeyEvent.VK_MULTIPLY}, true);
			addAction(DebugAction.DEBUG_PAUSE, new int[] {KeyEvent.VK_P}, true);
			addAction(DebugAction.PAUSE_PLUS_ONE_FRAME, new int[] {KeyEvent.VK_A}, true);
			addAction(DebugAction.DISPLAY_HELP, new int[] {KeyEvent.VK_F1});

			
		}
		private void addAction(DebugAction action, int[] keys) {
			addAction(action, keys, false);
		}
		private void addAction(DebugAction action, int[] keys, boolean ctrl) {
			actionKeyMap.put(action, keys);
			actionCtrl.put(action, ctrl);
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			if (callback instanceof AbstractGameFight) {
				AbstractGameFight game = (AbstractGameFight) callback;
				for (DebugAction action : actionKeyMap.keySet()) {
					if (e.getKeyCode() == actionKeyMap.get(action)[0]) {

						if (actionCtrl.get(action).booleanValue()
								&& e.isControlDown()) {
							game.onDebugAction(action);
						} else if (!actionCtrl.get(action).booleanValue()) {
							game.onDebugAction(action);
						}
					}
				}
				
			}
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	@Override
	public MouseCtrl getMouseStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getKeyEsc() {
		return 0;
	}

	@Override
	public int getKeyF1() {
		return 0;
	}

	@Override
	public int getKeyF2() {
		return 0;
	}

	@Override
	public int getKeyF3() {
		return 0;
	}

	@Override
	public void addActionListener(final MugenKeyListener key) {
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				key.action(e.getKeyCode(), true);
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				key.action(e.getKeyCode(), false);
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	@Override
	public void clearListener() {
		for (KeyListener kl : getKeyListeners())
			removeKeyListener(kl);
		addKeyListener(debugEventManager);
		
	}
	boolean isRender = true;

	@Override
	public boolean isRender() {
		return isRender;
	}

	@Override
	public void render() {

	}

	@Override
	public void removeSpriteKeysProcessors() {

	}

	@Override
	public void setRender(boolean isRender) {
		this.isRender = isRender;
	}
}
