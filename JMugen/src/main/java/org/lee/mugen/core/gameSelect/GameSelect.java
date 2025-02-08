package org.lee.mugen.core.gameSelect;

import java.awt.Point;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

import org.lee.mugen.core.Game;
import org.lee.mugen.core.GameFight;
import org.lee.mugen.core.GameMenu;
import org.lee.mugen.core.GameVsScreen;
import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.core.renderer.game.StageBackgroundRender;
import org.lee.mugen.core.renderer.game.select.SelectRender;
import org.lee.mugen.core.sound.SoundSystem;
import org.lee.mugen.fight.select.Characters;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.fight.system.elem.StageDisplay;
import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Teammode.TeamMode;
import org.lee.mugen.stage.Stage;

public class GameSelect implements Game {
	class ChangeStageThread extends Thread {
		@Override
		public void run() {
			try {
				String path = JMugenConstant.RESOURCE + MugenSystem.getInstance().getFiles().getSelect().getExtraStages().getStages().get(getIndexOfStage());
				Stage newStage = new Stage(path);
				oldStageToDelete = stage;
				stage = newStage;
				StageDisplay stageDisplay = MugenSystem.getInstance().getSelectInfo().getStagedisplay();
				if (stageDisplay != null) {
					StageBackgroundRender newStageBackgroundRender = new StageBackgroundRender(stage);
//					JoglMugenDrawer.createImageToTextPreparer();
					stageBackgroundRender = newStageBackgroundRender;
					stage.getCamera().setForcePos(true);
					
				}
				changeStage = false;
				processStage = false;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				threadStart.set(false);
			}
		}
	}
	private static final GameSelect instance = new GameSelect();
	public static GameSelect getInstance() {
		return instance;
	}
	
	public boolean backToMenu = false;
	
	boolean changeStage;

	int getIndexOfStage() {
		return SelectionController.getShareIndexOfStage();
	}
	private Game next;

	private String nextMode;

	Stage oldStageToDelete;
	
	boolean processStage;
	
	private SelectRender selectRender;
	Stage stage = null;

	StageBackgroundRender stageBackgroundRender;

	AtomicBoolean threadStart = new AtomicBoolean();
	
	String title = "";

	private GameSelect() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void free() {
		// TODO Auto-generated method stub
		
	}
	public String getCurrentStageNameFile() {
		return MugenSystem.getInstance().getFiles().getSelect().getExtraStages().getStages().get(getIndexOfStage());
	}
	@Override
	public Game getNext() throws Exception {
		return next == null? this: next;
	}
	public String getNextMode() {
		return nextMode;
	}

	private String getSelectedSprite(Point p) {
		int row = MugenSystem.getInstance().getSelectInfo().getRows();
		int col = MugenSystem.getInstance().getSelectInfo().getColumns();
		Characters characters = MugenSystem.getInstance().getFiles().getSelect().getCharacters();
		Iterator<String> iterCharacter = characters.getCharactersOrder().iterator();
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				
				if (iterCharacter.hasNext()) {
					String character = iterCharacter.next();
					if (p.x == r && p.y == c) {
						return character;
					}
				}
				
			}
		}
		return null;
	}
	public Stage getStage() {
		return stage;
	}
	
	public StageBackgroundRender getStageBackgroundRender() {
		return stageBackgroundRender;
	}
	GameWindow gameWindow;
	@Override
	public void init(GameWindow container) throws Exception {
		SoundSystem.SoundBackGround.stopMusic();
		SoundSystem.SoundBackGround.playMusic(JMugenConstant.RESOURCE + MugenSystem.getInstance().getMusic().getSelect$bgm());

		next = null;
		selectRender = new SelectRender(this);
		gameWindow = container;
		container.clearListener();
		reInit(container);
		initMode();
		
	}
	SelectionController[] selectionControllers = null;
	public SelectionController[] getSelectionControllers() {
		return selectionControllers;
	}
	public boolean isOverlap(SelectionController pSc) {
		for (SelectionController sc: selectionControllers) {
			if (pSc != sc) {
				if (pSc.getPosition().equals(sc.getPosition()))
					return true;
			}
		}
		return false;
	}

	int mode;
	
	public int getMode() {
		return mode;
	}



	public void setMode(int mode) {
		this.mode = mode;
	}



	public void initMode() {
		
		selectionControllers = new SelectionController[] {
				new SelectionController(this, "1", MugenSystem.getInstance().getSelectInfo().getP1().getCursor().getStartcell())
				,new SelectionController(this, "2", MugenSystem.getInstance().getSelectInfo().getP2().getCursor().getStartcell())
				
		};
		SelectionController.initShareVaraiable();
		gameWindow.clearListener();
		for (SelectionController sc: selectionControllers)
			gameWindow.addActionListener(sc);
	}

	
	@Override
	public void reInit(GameWindow container) throws Exception {
//		if (stage != null)
//			stage.free();
//		stage = null;
		stageBackgroundRender = null;
//		indexOfStage = 0;
		changeStage = true;
		backToMenu = false;
	}
	@Override
	public void render() throws Exception {
//		if (SelectionController.isAllPlayersSelectedButNoStage(selectionControllers.length)) {
//			stageBackgroundRender.render();
//		} else {
			selectRender.render();
//		}
	}
	public void setNextMode(String nextMode) {
		this.nextMode = nextMode;
	}
	public void setTilte(String title) {
		this.title = title;		
	}
	@Override
	public void update(int delta) throws Exception {
		if (backToMenu) {
			next = GameMenu.getInstance();
			return;
		}
		if (MugenSystem.getInstance().getSelectInfo().getStagedisplay() != null &&
				MugenSystem.getInstance().getSelectInfo().getStagedisplay().isEnable()) {
			if (changeStage) {
				processStage = true;
				if (!threadStart.get()) {
					threadStart.set(true);
					new ChangeStageThread().start();
					
				}
				
			} else {
				if (oldStageToDelete != null) {
					oldStageToDelete.free();
					oldStageToDelete = null;
				}
				StageDisplay stageDisplay = MugenSystem.getInstance().getSelectInfo().getStagedisplay();
				if (stageDisplay != null) {
//					if (SelectionController.isAllPlayersSelectedButNoStage(selectionControllers.length)) {
//						stage.getCamera().setX(0);
//						stage.getCamera().setY(0);
//						
//					} else {
//						stage.getCamera().setX(stageDisplay.getCamera().x);
						stage.getCamera().setY(stageDisplay.getCamera().y);
//					}
					stage.process();
				}
			}
		}
		MugenSystem.getInstance().getSelectBackground().process();
		
		// Launch the game
		int playerCount = selectionControllers.length;
		if (SelectionController.isAllSelectionDone(playerCount)) {
			launchGameVsScreen();
		}
		
	}
	private void launchGameVsScreen() {
		final GameVsScreen gameVsScreen = new GameVsScreen(this);
		next = gameVsScreen;
	}

	public String getSelectedSpriteName(int index) {
		return getSelectedSprite(selectionControllers[index].getPosition());
	}
	
	public Game getGameFight() throws Exception {
		GameFight.clear();
		final GameFight gameFight = GameFight.getInstance();
		gameFight.setTeamOneMode(TeamMode.SINGLE);
		gameFight.setTeamTwoMode(TeamMode.SINGLE);
		
		String name = getSelectedSprite(selectionControllers[0].getPosition());
		Sprite sprite = MugenSystem.getInstance().getFiles().getSelect().getCharacters().getInstanceOfSprite(name);
		sprite.setPal(0);
		sprite.setSpriteId("1");
		gameFight.addSprite(sprite, GameFight.TEAMSIDE_ONE);
		
		name = getSelectedSprite(selectionControllers[1].getPosition());

		sprite = MugenSystem.getInstance().getFiles().getSelect().getCharacters().getInstanceOfSprite(name);
		sprite.setPal(0);
		sprite.setSpriteId("2");
		gameFight.addSprite(sprite, GameFight.TEAMSIDE_TWO);
		if (stage == null) {
			String path = JMugenConstant.RESOURCE + MugenSystem.getInstance().getFiles().getSelect().getExtraStages().getStages().get(getIndexOfStage());
			stage = new Stage(path);
		}
		{
//			ObjectOutput out = new ObjectOutputStream(new FileOutputStream("stage.ser"));
//			out.writeObject(stage);
//			out.close();

//			File file = new File("stage.ser");
//			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
//			stage = (Stage) in.readObject();
		}

        
		gameFight.setStage(stage);
		
		return gameFight;
	}



	public void setStageChanged() {
		changeStage = true;
		
	}
	public void setBackToMenu() {
		backToMenu = true;
		
	}
}
