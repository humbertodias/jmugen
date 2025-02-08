package org.lee.mugen.test;

import java.io.File;
import java.lang.reflect.Method;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;

import org.lee.mugen.core.GameFight;
import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.core.sound.SoundSystem;
import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Teammode.TeamMode;
import org.lee.mugen.stage.Stage;
import org.lee.mugen.util.debugger.SpriteDebugerUI;

public class TestGameFight  {


	public static void launchUI() throws Exception {

		final GameFight statemachine = GameFight.getInstance();

	    String nativeLF = UIManager.getSystemLookAndFeelClassName();
	    
	    // Install the look and feel
	    try {
	        UIManager.setLookAndFeel(nativeLF);
	    } catch (InstantiationException e) {
	    } catch (ClassNotFoundException e) {
	    } catch (UnsupportedLookAndFeelException e) {
	    } catch (IllegalAccessException e) {
	    }

		String p1 = null;
		String p2 = null;
		String stage = null;
		String music = null;
		
		JOptionPane.showMessageDialog(null, 
				"JMugen : This version is an alpha version" + "\n" + 
				"* Memory is not optimized but now it is better with jogl" + "\n" + 
				"* There are two version of this JMugen (Default is Opengl)" + "\n" + 
				"    - OpenGL (For now Shader 2 is requiered)" + "\n" + 
				"    - Software (no support for now palFx,proper rotation, ...)" + "\n" + 
				"* Key Mapping => edit The file keys.properties" + "\n" + 
				"* To change Renderer edit the file render.properties" + "\n" + 
				"" + "\n" + 
				"For Hi Res Stage support you have to add in the " + "\n" + 
				"    file Stage.def in [Scaling] section theses 2 new element xscale = 0.5 and yscale = 0.5" + "\n" + 
				"" + "\n" + 
				"*- No Implemted Yet and very need by Mugen " + "\n" + 
				"    - Reversedef " + "\n" +
				"*- WIP : " + "\n" + 
				"    1 - Parser Speed Optimisation " + "\n" + 
				"    (RAOH's state.cns is about 1.5M and have many many expressions, " + "\n" + 
				"     it take about 12 sec to load it all)" + "\n" + "\n" + 
				"    2 - ReversalDef (it might be a complicate part)" + "\n" + 
				"    3 - I'll see" + "\n" + 
				"" + "\n" + 
				"* Note : - you have to keep this directory stucture and you will need this file : resource\\data\\common.cmd" + "\n" + 
				"           This File is a list of controller that append the statedef -1 and it is use for controlling the sprite " +  "\n" +
				"         - There is a Debugger windows, you can change on the fly the state, to use it , once the Two sprite appears click on 'click here'" + "\n" +
				"\n" + 
				"-------------------------------------------------------------------------------------------------------\n" + 
				"Now it will prompt to choose Characters, stage and music (music is not required)", "JMugen 0.01b", JOptionPane.INFORMATION_MESSAGE);
		
		JOptionPane.showMessageDialog(null, "Choose the first Char", "JMugen 0.01b", JOptionPane.INFORMATION_MESSAGE);
		JFileChooser fcSelectChar = new JFileChooser(new File(".", JMugenConstant.RESOURCE + "chars"));
		fcSelectChar.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".def") || f.isDirectory();
			}
			@Override
			public String getDescription() {
				return "Sprite *.def";
			}});
		fcSelectChar.showOpenDialog(null);
		File selFile = fcSelectChar.getSelectedFile();
		if (selFile != null) {
			p1 = selFile.getAbsolutePath();
		} else {
			JOptionPane.showMessageDialog(null, "This option is a mandatory");
			System.exit(0);
		}
		
		
		JOptionPane.showMessageDialog(null, "Choose the second Char", "JMugen 0.01b", JOptionPane.INFORMATION_MESSAGE);
		fcSelectChar = new JFileChooser(new File(".", JMugenConstant.RESOURCE + "chars"));
		fcSelectChar.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".def") || f.isDirectory();
			}
			@Override
			public String getDescription() {
				return "Sprite *.def";
			}});
		fcSelectChar.showOpenDialog(null);
		selFile = fcSelectChar.getSelectedFile();
		if (selFile != null) {
			p2 = selFile.getAbsolutePath();
		} else {
			JOptionPane.showMessageDialog(null, "This option is a mandatory");
			System.exit(0);
		}
		
		
		JOptionPane.showMessageDialog(null, "Choose Stage", "JMugen 0.01b", JOptionPane.INFORMATION_MESSAGE);
		fcSelectChar = new JFileChooser(new File(".", JMugenConstant.RESOURCE + "stages"));
		fcSelectChar.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".def") || f.isDirectory();
			}
			@Override
			public String getDescription() {
				return "Stage *.def";
			}});
		fcSelectChar.showOpenDialog(null);
		selFile = fcSelectChar.getSelectedFile();
		if (selFile != null) {
			stage = selFile.getAbsolutePath();
		} else {
			JOptionPane.showMessageDialog(null, "This option is a mandatory");
			System.exit(0);
		}
		
		
		JOptionPane.showMessageDialog(null, "Choose Music", "JMugen 0.01b", JOptionPane.INFORMATION_MESSAGE);
		fcSelectChar = new JFileChooser(new File(".", JMugenConstant.RESOURCE + "sound"));
		fcSelectChar.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".mp3") || f.getName().toLowerCase().endsWith(".wav")  || f.isDirectory();
			}
			@Override
			public String getDescription() {
				return "Music *.mp3|*.wav";
			}});
		fcSelectChar.showOpenDialog(null);
		selFile = fcSelectChar.getSelectedFile();
		if (selFile != null) {
			music = selFile.getAbsolutePath();
		}
		
		
		
//		statemachine.getGameState().setGameType(TeamMode.SINGLE); // TODO
		statemachine.setTeamOneMode(TeamMode.SINGLE);
		statemachine.setTeamTwoMode(TeamMode.SINGLE);
		
		statemachine.preloadSprite(GameFight.TEAMSIDE_ONE, "1", p1, 0);
		statemachine.preloadSprite(GameFight.TEAMSIDE_TWO, "2", p2, 0);
		

		
		statemachine.setStage(new Stage(stage));
		if (music != null)
			SoundSystem.SoundBackGround.playMusic(music);

		
		GraphicsWrapper.init();
		final GameWindow gameWindow = GraphicsWrapper.getInstance()
				.getInstanceOfGameWindow();
		gameWindow.setGameWindowCallback(statemachine);
		gameWindow.start();
		
		
		Method m = gameWindow.getClass().getDeclaredMethod("isFinishInit");
		SpriteDebugerUI debugerUI = new SpriteDebugerUI();
		while (!(Boolean)m.invoke(gameWindow))
			Thread.sleep(1000);
		debugerUI.setVisible(true);
		ExpressionTester.lanch();
		
	}
	public static void main(String[] args) throws Exception {
		launchDirect();
//		launchUI();
		
//		SoundSystem.SoundBackGround.playMusic(JMugenConstant.RESOURCE + "sound/ADX_S060.adx");
	}
	
	public static void launchDirect() throws Exception {

		
	    String nativeLF = UIManager.getSystemLookAndFeelClassName();
	    
	    // Install the look and feel
	    try {
	        UIManager.setLookAndFeel(nativeLF);
	    } catch (InstantiationException e) {
	    } catch (ClassNotFoundException e) {
	    } catch (UnsupportedLookAndFeelException e) {
	    } catch (IllegalAccessException e) {
	    }
	    
	    
		String[] args = new String[] {
				"kfm", "0", 
				"kfm", "0",
//				"kenstageDG.def",
				"stage0.def",
//				"mvc2/ADX_S060.BIN"
		};
		
		final GameFight statemachine = GameFight.getInstance();
//		statemachine.getGameState().setGameType(TeamMode.SINGLE); // TODO
		statemachine.setTeamOneMode(TeamMode.SINGLE);
		statemachine.setTeamTwoMode(TeamMode.SINGLE);
		statemachine.preloadSprite(GameFight.TEAMSIDE_ONE, "1", JMugenConstant.RESOURCE + "chars/" + args[0] + "/" + args[0] + ".def", Integer.parseInt(args[1]));
		statemachine.preloadSprite(GameFight.TEAMSIDE_TWO, "2", JMugenConstant.RESOURCE + "chars/" + args[2] + "/" + args[2] + ".def", Integer.parseInt(args[3]));



		
		GraphicsWrapper.init();
		statemachine.setStage(JMugenConstant.RESOURCE + "stages/" + args[4]);
//		if (args.length > 5)
//			SoundSystem.SoundBackGround.playMusic(JMugenConstant.RESOURCE + "sound/" + args[5]);
		final GameWindow gameWindow = GraphicsWrapper.getInstance().getInstanceOfGameWindow();
		gameWindow.setGameWindowCallback(statemachine);
		SpriteDebugerUI debugerUI = new SpriteDebugerUI();
		
		gameWindow.setRender(true);
//		debugerUI.setVisible(true);
//		ExpressionTester.lanch();
		gameWindow.start();
//		Method m = gameWindow.getClass().getDeclaredMethod("isFinishInit");
//
//		while (!(Boolean)m.invoke(gameWindow))
//			Thread.sleep(1000);
//		debugerUI.setVisible(true);
//		ExpressionTester.lanch();

	}

}