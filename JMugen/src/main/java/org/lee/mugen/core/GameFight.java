package org.lee.mugen.core;

import static org.lee.mugen.util.Logger.log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.lee.mugen.core.command.SpriteCmdProcess;
import org.lee.mugen.core.gameSelect.GameSelect;
import org.lee.mugen.core.renderer.game.AfterimageRender;
import org.lee.mugen.core.renderer.game.CnsRender;
import org.lee.mugen.core.renderer.game.DebugExplodRender;
import org.lee.mugen.core.renderer.game.DebugRender;
import org.lee.mugen.core.renderer.game.ExplodRender;
import org.lee.mugen.core.renderer.game.MakedustRender;
import org.lee.mugen.core.renderer.game.ProjectileRender;
import org.lee.mugen.core.renderer.game.SpriteRender;
import org.lee.mugen.core.renderer.game.SpriteShadowRender;
import org.lee.mugen.core.renderer.game.StageBackgroundRender;
import org.lee.mugen.core.renderer.game.fight.FightdefRender;
import org.lee.mugen.core.renderer.game.fight.RoundRender;
import org.lee.mugen.core.sound.SoundSystem;
import org.lee.mugen.fight.section.Fightdef;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.input.CmdProcDispatcher;
import org.lee.mugen.input.ISpriteCmdProcess;
import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.MugenTimer;
import org.lee.mugen.renderer.Renderable;
import org.lee.mugen.renderer.GameWindow.MugenKeyListener;
import org.lee.mugen.sprite.base.AbstractSprite;
import org.lee.mugen.sprite.character.Sprite;
import org.lee.mugen.sprite.character.SpriteDef;
import org.lee.mugen.sprite.character.SpriteHelper;
import org.lee.mugen.sprite.cns.StateDef;
import org.lee.mugen.sprite.cns.eval.trigger.function.spriteCns.Teammode.TeamMode;
import org.lee.mugen.sprite.cns.type.function.Assertspecial;
import org.lee.mugen.sprite.common.resource.FontParser;
import org.lee.mugen.sprite.common.resource.FontProducer;
import org.lee.mugen.sprite.entity.AfterImageSprite;
import org.lee.mugen.sprite.entity.ExplodSprite;
import org.lee.mugen.sprite.entity.MakeDustSpriteManager;
import org.lee.mugen.sprite.entity.ProjectileSprite;
import org.lee.mugen.stage.Stage;
import org.lee.mugen.util.Logger;

/**
 * 
 * @author Dr Wong
 * 
 * StateMachine is the core of the program
 */
public class GameFight implements AbstractGameFight {
	// TeamMate
	public static final int TEAMSIDE_ONE = 1;
	public static final int TEAMSIDE_TWO = 2;
	private TeamMode teamOneMode;
	private TeamMode teamTwoMode;

	private Map<String, Sprite> _teamOne = new HashMap<String, Sprite>();
	public TeamMode getTeamOneMode() {
		return teamOneMode;
	}
	public void setTeamOneMode(TeamMode teamOneMode) {
		this.teamOneMode = teamOneMode;
	}
	public TeamMode getTeamTwoMode() {
		return teamTwoMode;
	}
	public void setTeamTwoMode(TeamMode teamTwoMode) {
		this.teamTwoMode = teamTwoMode;
	}

	private Map<String, Sprite> _teamTwo = new HashMap<String, Sprite>();
	
	public Map<String, Sprite> getTeamOne() {
		return _teamOne;
	}
	public Map<String, Sprite> getTeamTwo() {
		return _teamTwo;
	}
	public boolean isSpriteInSameTeam(String id, String id2) {
		return (_teamOne.containsKey(id) && _teamOne.containsKey(id2)) || (_teamTwo.containsKey(id) && _teamTwo.containsKey(id2));
	}




//	 Core
	private static GameFight stateMachine = null;
	private GameWindow gameWindows;
	private FightEngine _fightEngine = new FightEngine();
	private GameState gameState = new GameState();
	private GameGlobalEvents globalEvents = new GameGlobalEvents();
	private Stage instanceOfStage;
	
	
	// Sprite Player Management
	private Map<String, SpriteDef> spriteDefs = new HashMap<String, SpriteDef>();
	private Map<String, SpriteCmdProcess> spriteCmdProcessMap = new HashMap<String, SpriteCmdProcess>();
	private Map<String, Sprite> _spriteMap = new HashMap<String, Sprite>();
	private List<AbstractSprite> _otherSprites = new LinkedList<AbstractSprite>();
	
	// Helper Sprite Temp List
	private List<SpriteHelper> _spriteHelperAddList = new LinkedList<SpriteHelper>();
	private List<String> _spriteHelperRemoveList = new ArrayList<String>();
	private List<SpriteLoader> spriteLoader = new ArrayList<SpriteLoader>();
	
	protected static class SpriteLoader {

		private String spriteId;
		private SpriteDef def;
		private int pal;
		private int teamSide;
		
		public SpriteLoader(String spriteId, SpriteDef def, int pal, int teamSide) {
			this.spriteId = spriteId;
			this.def = def;
			this.pal = pal;
			this.teamSide = teamSide;
		}
		
		public SpriteDef getDef() {
			return def;
		}
		public void setDef(SpriteDef def) {
			this.def = def;
		}
		public int getPal() {
			return pal;
		}
		public void setPal(int pal) {
			this.pal = pal;
		}
		public String getSpriteId() {
			return spriteId;
		}
		public void setSpriteId(String spriteId) {
			this.spriteId = spriteId;
		}
		public int getTeamSide() {
			return teamSide;
		}
		public void setTeamSide(int teamSide) {
			this.teamSide = teamSide;
		}
		
	}
	
	// Renderer Sprite
	private Map<String, Renderable> spriteMapRenderable = new HashMap<String, Renderable>();
	private List<Renderable> renderableList = new ArrayList<Renderable>();
	private List<Renderable> backgroundRenderList = new ArrayList<Renderable>();
	private LinkedList<Renderable> renderableListTemp = new LinkedList<Renderable>();


	
	
	// System MAnagement

	
	
	
	
	
	
	
	
	
	public GameWindow getWindow() {
		return gameWindows;
	}
	public void setWindow(GameWindow gameWindows) {
		this.gameWindows = gameWindows;
	}

	public FightEngine getFightEngine() {
		return _fightEngine;
	}
	public GameState getGameState() {
		return gameState;
	}
	
	// Singleton
	private GameFight() {
	}

	public static GameFight getInstance() {
		if (stateMachine == null) {
			stateMachine = new GameFight();
		}
		return stateMachine;
	}
	public static void clear() {
		stateMachine.free();
		stateMachine = null;
	}
	
	// global events Events 

	public GameGlobalEvents getGlobalEvents() {
		return globalEvents;
	}
	
	
	
	// Stage

	public void setStage(Stage stage) throws Exception {
		removeBackgroundRender();
		if (instanceOfStage != null)
			instanceOfStage.free();
		instanceOfStage = stage;
		this.stage = instanceOfStage.getFilename();
		instanceOfStage.getCamera().setForcePos(false);
		addRender(new StageBackgroundRender(instanceOfStage));
		reloadStage = false;
	}

	public Stage getStage() {
		return instanceOfStage;
	}
	

	
	public SpriteDef getSpriteDef(String spriteId) {
		return spriteDefs.get(spriteId);
	}
	public Collection<Sprite> getSprites() {
		List<Sprite> sprites = new LinkedList<Sprite>();
		sprites.addAll(_spriteMap.values());
		return sprites;
	}
	public Collection<String> getPlayersIds() {
		return _spriteMap.keySet();
	}
	public Sprite getSpriteInstance(String spriteId) {
		Sprite spr = _spriteMap.get(spriteId);
		return spr;
	}
	public Renderable getSpriteRenderInstance(String spriteId) {
		return spriteMapRenderable.get(spriteId);
	}
	
	

	
	public synchronized void addSpriteHelper(SpriteHelper sprClone) {
		_spriteHelperAddList.add(sprClone);
		
		_spriteMap.put(sprClone.getSpriteId(), sprClone);
		spriteMapRenderable.put(sprClone.getSpriteId(), new SpriteRender(sprClone));
		addRender(spriteMapRenderable.get(sprClone.getSpriteId()));
		ISpriteCmdProcess scp = getSpriteCmdProcessMap().get(sprClone.getHelperSub().getSpriteFrom().getSpriteId());
		addPartner(sprClone, sprClone.getHelperSub().getSpriteFrom());
		sprClone.getSpriteState().changeStateDef(sprClone.getHelperSub().getStateno());
	}
	public void removeSpriteHelper(String sprCloneId) {
		_spriteHelperRemoveList.add(sprCloneId);
	}
	
	private void helperManagement() {
		if (!_spriteHelperAddList.isEmpty()) {
			
			SpriteHelper[] helpers = _spriteHelperAddList.toArray(new SpriteHelper[0]);
			_spriteHelperAddList.clear();
			for (SpriteHelper sprClone: helpers) {
				_spriteMap.put(sprClone.getSpriteId(), sprClone);
				spriteMapRenderable.put(sprClone.getSpriteId(), new SpriteRender(sprClone));
				addRender(spriteMapRenderable.get(sprClone.getSpriteId()));
				ISpriteCmdProcess scp = getSpriteCmdProcessMap().get(sprClone.getHelperSub().getSpriteFrom().getSpriteId());
				sprClone.getSpriteState().changeStateDef(sprClone.getHelperSub().getStateno());
				addPartner(sprClone, sprClone.getHelperSub().getSpriteFrom());
			}
			
		}

		if (!_spriteHelperRemoveList.isEmpty()) {
			for (String spriteId: _spriteHelperRemoveList) {
				Sprite spr = getSpriteInstance(spriteId);
				if (spr == null || spr.getClass() == Sprite.class)
					continue;
				removePartner(spr);
				
				renderableList.remove(spriteMapRenderable.get(spriteId));
				spriteMapRenderable.remove(spriteId);
				_spriteMap.remove(spriteId);
				
				if (spr instanceof SpriteHelper) {
//					log("destroy " + spriteId);
					SpriteHelper sprH = (SpriteHelper) spr;
					ISpriteCmdProcess scp = getSpriteCmdProcessMap().get(sprH.getHelperSub().getSpriteFrom().getSpriteId());
					if (scp != null)
						scp.remove(sprH.getSpriteId());
				}
			}
			_spriteHelperRemoveList.clear();
		}
	}
	


	public void preloadSprite(int teamSide, String spriteId, SpriteDef def, int pal) {
		SpriteLoader sl = new SpriteLoader(spriteId, def, pal, teamSide);
		spriteLoader.add(sl);
	}
	private void loadSprites() throws Exception {
		if (!spriteLoader.isEmpty()) {
			for (SpriteLoader sl: spriteLoader) {
				log("Load Sprite " + sl.def);
				loadingText += "\nloading Sprite " + sl.getSpriteId() + " : ";
				loadSprite(sl);
			}
			spriteLoader.clear();			
		}
	}

	public void addSprite(Sprite spr, int teamSide) throws FileNotFoundException, IOException {
			
		spriteDefs.put(spr.getSpriteId(), spr.getDefinition());
		spr.buildSpriteSff();
		if (teamSide == TEAMSIDE_ONE) {
			getTeamOne().put(spr.getSpriteId(), spr);
		} else if (teamSide == TEAMSIDE_TWO) {
			getTeamTwo().put(spr.getSpriteId(), spr);
			
		}
		_spriteMap.put(spr.getSpriteId(), spr);
		spriteMapRenderable.put(spr.getSpriteId(), new SpriteRender(spr));
		addRender(spriteMapRenderable.get(spr.getSpriteId()));
	}
	
	private void loadSprite(SpriteLoader sprLoader)
		throws Exception {
		Sprite spr = _spriteMap.get(sprLoader.getSpriteId());
		if (spr == null) {
			log("Load Sprite def");
			SpriteDef sprDef = sprLoader.getDef();
			log("End Load Sprite def");

			spriteDefs.put(sprLoader.getSpriteId(), sprDef);
			log("Load Sprite");
			spr = new Sprite(sprLoader.getSpriteId(), sprDef, sprLoader.getPal());
			spr.buildSpriteSff();
			log("End Load Sprite");
			if (sprLoader.getTeamSide() == TEAMSIDE_ONE) {
				getTeamOne().put(sprLoader.getSpriteId(), spr);
			} else if (sprLoader.getTeamSide() == TEAMSIDE_TWO) {
				getTeamTwo().put(sprLoader.getSpriteId(), spr);
				
			}
			_spriteMap.put(sprLoader.getSpriteId(), spr);
			spriteMapRenderable.put(sprLoader.getSpriteId(), new SpriteRender(spr));
			addRender(spriteMapRenderable.get(sprLoader.getSpriteId()));
		} else {
			Sprite old = getSpriteInstance(sprLoader.getSpriteId());
			int teamSide = 0;
			if (getTeamOne().containsKey(old.getSpriteId())) {
				teamSide = TEAMSIDE_ONE;
			} else if (getTeamTwo().containsKey(old.getSpriteId())) {
				teamSide = TEAMSIDE_TWO;
			}
			
			SpriteDef sprDef = sprLoader.getDef();
			spriteDefs.put(sprLoader.getSpriteId(), sprDef);
			spr = new Sprite(sprLoader.getSpriteId(), sprDef, sprLoader.getPal());
			
			if (teamSide == TEAMSIDE_ONE) {
				getTeamOne().put(spr.getSpriteId(), spr);
			} else if (teamSide == TEAMSIDE_TWO) {
				getTeamTwo().put(spr.getSpriteId(), spr);
			}
			_spriteMap.put(sprLoader.getSpriteId(), spr);
			renderableList.remove(spriteMapRenderable.get(sprLoader.getSpriteId()));
			spriteMapRenderable.put(sprLoader.getSpriteId(), new SpriteRender(spr));
			addRender(spriteMapRenderable.get(sprLoader.getSpriteId()));
			
		}
	}
	
	// Others Sprite
	

	public List<AbstractSprite> getOtherSprites() {
		return _otherSprites;
	}



	

	
	public void addRender(Renderable r) {
		renderableListTemp.add(r);
	}
	
	public void flipRender() {
		synchronized (renderableList) {
			while (!renderableListTemp.isEmpty()) {
//				Renderable r = renderableListTemp.pollLast();
				Renderable r = renderableListTemp.removeLast();
				if (r instanceof StageBackgroundRender) {
					backgroundRenderList.add((StageBackgroundRender) r);
					return;
				}
				if (!renderableList.contains(r))
					renderableList.add(r);
			}
		}
//		renderableList.addAll(renderableListTemp);
//		renderableListTemp.clear();
	}
	private void removeBackgroundRender() {
		backgroundRenderList.clear();
	}


	
	public List<Renderable> getRenderables() {
		return renderableList;
	}
	public ExplodRender getExplodRender(ExplodSprite s) {
		for (Renderable r: renderableList) {
			if (r instanceof ExplodRender) {
				ExplodRender er = (ExplodRender) r;
				if (er.getSprite() == s)
					return er;
			}
		}
		return null;
	}
	
	public void removeRender(AbstractSprite s) {
		for (Iterator<Renderable> iter = renderableList.iterator(); iter.hasNext();) {
			Renderable r = iter.next();
			if (r instanceof ExplodRender) {
				ExplodRender er = (ExplodRender) r;
				if (er.getSprite() == s) {
					iter.remove();
					return;
				}
			} else if (r instanceof ProjectileRender) {
				ProjectileRender er = (ProjectileRender) r;
				if (er.getSprite() == s) { 
					iter.remove();
					return;
				}
			} else if (r instanceof SpriteShadowRender) {
				SpriteShadowRender er = (SpriteShadowRender) r;
				if (er.getSprite() == s) {
					iter.remove();
					return;
				}
			}
			
		}
	}


	private Comparator<Renderable> _DEFAULT_COMPARATOR_FOR_RENDERER = new Comparator<Renderable>() {
		public int compare(Renderable o1, Renderable o2) {
			return o1.getPriority() - o2.getPriority();
		}
	};
	private Fightdef fightdef;

	public void orderRenderList() {
		Collections.sort(renderableList, _DEFAULT_COMPARATOR_FOR_RENDERER);
	}




	

	
	public Fightdef getFightdef() {
		return fightdef;
	}
	public void setFightDef(Fightdef fightDef) {
		this.fightdef  = fightDef;
	}
	////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////
	List<CnsRender> cnsRenderList = new ArrayList<CnsRender>();
	boolean addListener;
	boolean freeNow;
	boolean reloadStage;
	public void init(final GameWindow container) throws Exception {
		SoundSystem.SoundBackGround.stopMusic();
		

		setWindow(container);
		next = null;
		freeNow = false;
		container.clearListener();
		if (!addListener) {
			container.addActionListener(new MugenKeyListener() {

				@Override
				public void action(int key, boolean isPress) {
					if (container.getKeyEsc() == key) {
						freeNow = true;
					} else if (container.getKeyF1() == key) {
//						reloadStage = true;
//						stage = instanceOfStage.getFilename();
					} else if (container.getKeyF2() == key && !isPress) {
						
						String newStage = stage.substring(JMugenConstant.RESOURCE.length());
						LinkedList<String> list = MugenSystem.getInstance().getFiles().getSelect().getExtraStages().getStages();
						int index = list.indexOf(newStage);
						index--;
						if (index < 0)
							index = list.size() - 1;
						stage = JMugenConstant.RESOURCE + list.get(index);
						reloadStage = true;
					} else if (container.getKeyF3() == key && !isPress) {
						
						String newStage = stage;
						if (stage.startsWith(JMugenConstant.RESOURCE))
							newStage = stage.substring(JMugenConstant.RESOURCE.length());
						LinkedList<String> list = MugenSystem.getInstance().getFiles().getSelect().getExtraStages().getStages();
						int index = list.indexOf(newStage);
						index++;
						if (index > list.size() - 1)
							index = 0;
						stage = JMugenConstant.RESOURCE + list.get(index);
						reloadStage = true;
					}
					
				}

			});
			addListener = true;
		}
		
		fightdef = MugenSystem.getInstance().getFiles().getFight();
		try {
			
			loadSprites();
			if (instanceOfStage == null) {
				setStage(new Stage(stage));
				SoundSystem.SoundBackGround.playMusic(JMugenConstant.RESOURCE + instanceOfStage.getMusic().getBgmusic());

			}
			loadingText += "\nloading Stage ";

			addRender(new SpriteShadowRender(getSpriteInstance("1"), false));
			addRender(new SpriteShadowRender(getSpriteInstance("2"), false));
			
			cnsRenderList.add(new CnsRender(getSpriteInstance("1")));
			cnsRenderList.add(new CnsRender(getSpriteInstance("2")));

			addRender(cnsRenderList.get(0));
			addRender(cnsRenderList.get(1));
			
			addRender(new FightdefRender());
			addRender(new RoundRender());
			

			MugenSystem ms = MugenSystem.getInstance();


//			addRender(new SpriteRender(new TypeSprite(getFightDef().getLifebar().getP1().getFront(), getFightDef().getLifebar().getP1().getPos())));

		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalStateException();
		}

		((GameWindow)container).removeSpriteKeysProcessors();

		for (Sprite s : getSprites()) {
			if (spriteCmdProcessMap.get(s) == null) {
				CmdProcDispatcher cmd = CmdProcDispatcher.getSpriteDispatcherMap().get(s.getSpriteId());
				if (cmd != null)
					cmd.clear();
				SpriteCmdProcess scp = new SpriteCmdProcess(cmd);
				scp.addSprite(s.getSpriteId());
				spriteCmdProcessMap.put(s.getSpriteId(), scp);
				((GameWindow)container).addSpriteKeyProcessor(scp);
			}
		}
		
		initRound();
	}

	
	private String stage;
	public void setStage(String stage) {
		this.stage = stage;
		reloadStage = true;
	}

	public void update(int delta) throws Exception {
		if (freeNow) {
			next = GameSelect.getInstance();
			return;
		}
		if (reloadStage) {
			Integer x = null;
			if (instanceOfStage != null && instanceOfStage.getCamera() != null) {
				x = instanceOfStage.getCamera().getXNoShaKe();
			}
			
			setStage(new Stage(this.stage));
			if (x != null)
				instanceOfStage.getCamera().setX(x);

		}
		if (!getGlobalEvents().canUpdate())
			return;
		for (Iterator<AbstractSprite> iter = getOtherSprites().iterator(); iter.hasNext();) {
			AbstractSprite spr = iter.next();
			if (spr.remove()) {
				removeRender(spr);
				iter.remove();
			}
		}
		
		getGlobalEvents().enter();
		getGameState().enter(this);

		for (Sprite s : getSprites()) {

			ISpriteCmdProcess sprCmdProc = spriteCmdProcessMap.get(s.getSpriteId());
			if (sprCmdProc != null) {
				sprCmdProc.process(s.getSpriteId());
			}

			if (globalEvents.canGameProcessWithPause(s)) {
				s.process();
			}
		}

		if (!globalEvents.isPause()) {
			getFightEngine().process();
		}
		
//		if (!globalEvents.isSuperPauseOrPause()) {
			for (Iterator<AbstractSprite> iter = getOtherSprites().iterator(); iter.hasNext();) {
				AbstractSprite spr = iter.next();
				if (spr instanceof ExplodSprite) {
					ExplodSprite es = (ExplodSprite) spr;
					if (es.getExplod().getSupermovetime() > 0 && globalEvents.isSuperPause()) {
						es.process();
					} else if (es.getExplod().getPausemovetime() > 0 && globalEvents.isPause()) {
						es.process();
					} else if (es.getExplod().isSupermove() && globalEvents.isSuperPause()) {
						es.process();
					} else if (!globalEvents.isSuperPause() && !globalEvents.isPause()) {
						es.process();
					} 
					else {
						es.process();
					}
						
				} else {
					spr.process();
					
				}

			}
		instanceOfStage.process();

		getGameState().leave(this);
		// 
		globalEvents.leave();
		
		loadSprites();
		helperManagement();
		fightdef.process();
		flipRender();
		
		/////////////


	}




	private void render(List<Renderable> rendererList) {
		orderRenderList();
		List<Renderable> toRemove = new ArrayList<Renderable>();
		for (Renderable renderable : rendererList) {
			if (globalEvents.getEnvcolor().isUse() && !globalEvents.getEnvcolor().isUnder()) {
				if (!globalEvents.canDisplayThisRender(renderable.getClass())) {
					continue;
				}
			}
			if (renderable.isProcess()) {
//				if (renderable instanceof ExplodRender)
					renderable.render();
			}
			if (renderable.remove()) {
				toRemove.add(renderable);
			}
		}
		rendererList.removeAll(toRemove);
	}
	
	public void render() {
		
		StageBackgroundRender br = (StageBackgroundRender) backgroundRenderList.get(0);
		br.setLayerDisplay(0);
		
		if (!globalEvents.getEnvcolor().isUse()) {
			if (!globalEvents.isAssertSpecial(Assertspecial.Flag.nobg)) {
				render(backgroundRenderList);
			}
		} else {
			globalEvents.getEnvcolorRender().render();
		}
		
		render(renderableList);
		
		br.setLayerDisplay(1);
		if (!globalEvents.getEnvcolor().isUse()) {
			if (!globalEvents.isAssertSpecial(Assertspecial.Flag.nobg)) {
				render(backgroundRenderList);
			}
		} else {
			
		}
	}

	public Map<String, SpriteCmdProcess> getSpriteCmdProcessMap() {
		return spriteCmdProcessMap;
	}


	public Map<String, StateDef> getInstanceOfStatedefFromOther(String spriteId) {
		Map<String, StateDef> stateDefsMap = getSpriteInstance(spriteId).getSpriteState().cloneStateNormalDef();
		return stateDefsMap;
	}
	
//////////////////////////////////////////////////
//////////////////////////////////////////////////
//////////////////////////////////////////////////
//////////////////////////////////////////////////
//////////////////////////////////////////////////
	
	public int countHelper(String spriteId) {
		int count = 0;
		for (Sprite s: getPartners(getSpriteInstance(spriteId))) {
			Sprite spr = getRoot(s);
			if (spr.getSpriteId().equals(spriteId) && !s.equals(spr)) {
				count++;
			}
		}
		return count;
	}

//	Parent : Redirige le trigger vers le parent du personnage (le personnage doit être un helper). Le parent d'un helper est le personnage qui a créé cet helper (et qui peut être soit le personnage de base, soit un autre helper).
	public Sprite getParent(Sprite spr) {
		if (!(spr instanceof SpriteHelper)) {
			return null;
		}
		return ((SpriteHelper) spr).getHelperSub().getSpriteFrom();
	}
	public Sprite getParent(String spriteId) {
		Sprite spr = getSpriteInstance(spriteId);
		return getParent(spr);
	}
	public String getParentId(Sprite spr) {
		if (!(spr instanceof SpriteHelper)) {
			return spr.getSpriteId();
		}
		return ((SpriteHelper) spr).getHelperSub().getSpriteFrom().getSpriteId();
	}
	public String getParentId(String spriteId) {
		Sprite spr = getSpriteInstance(spriteId);
		return getParentId(spr);
	}
//	Root : Redirige le trigger vers la base. Le root d'un helper est le personnage de base (équivaut donc au parent pour un helper directement créé par le personnage de base).

	public Sprite getRoot(Sprite spr) {
		if (!(spr instanceof SpriteHelper)) {
			return spr;
		}
		return getRoot(((SpriteHelper) spr).getHelperSub().getSpriteFrom());
	}
	public Sprite getRoot(String spriteId) {
		Sprite spr = getSpriteInstance(spriteId);
		return getRoot(spr);
	}
	public String getRootId(Sprite sprite) {
		Sprite root = getRoot(sprite);
		if (root != null)
			return getRoot(sprite).getSpriteId();
		else
			return null;
	}
	public String getRootId(String spriteId) {
		Sprite spr = getSpriteInstance(spriteId);
		return getRootId(spr);
	}
	
//	Helper : Redirige le trigger vers le premier helper trouvé. Voir le trigger associé "NumHelper" dans la documentation des trigger.
	public Sprite getHelper(Sprite sprite) {
		for (Sprite s: getPartners(sprite)) {
			if (!s.equals(sprite) && (sprite instanceof SpriteHelper)) {
				SpriteHelper helper = (SpriteHelper) s;
				if (helper.getHelperSub().getSpriteFrom().equals(sprite))
					return helper;
			}
		}
		return null;
	}
	
	public List<SpriteHelper> getHelpers(Sprite sprite) {
		List<SpriteHelper> result = new ArrayList<SpriteHelper>();
		for (Sprite s: getPartners(sprite)) {
			if (!s.equals(sprite) && (sprite instanceof SpriteHelper)) {
				SpriteHelper helper = (SpriteHelper) s;
				if (helper.getHelperSub().getSpriteFrom().equals(sprite))
					result.add(helper);
			}
		}
		return result;
	}
	
	public Sprite getHelper(String spriteId) {
		Sprite spr = getSpriteInstance(spriteId);
		return getHelper(spr);
	}
	public String getHelperId(Sprite sprite) {
		Sprite helper = getHelper(sprite);
		if (helper != null)
			return helper.getSpriteId();
		return null;
	}
	public String getHelperId(String spriteId) {
		Sprite spr = getSpriteInstance(spriteId);
		return getHelperId(spr);
	}
	
//	Helper(ID) : ID doit être une expression correct qui donne un entier positif. Le trigger est alors redirigé vers un helper avec le numéro ID correspondant.

	public Sprite getHelperWithID(Sprite sprite, int id) {
		for (Sprite s: getPartners(sprite)) {
			if (!s.equals(sprite) && (sprite instanceof SpriteHelper)) {
				SpriteHelper helper = (SpriteHelper) s;
				if (helper.getHelperSub().getSpriteFrom().equals(sprite) 
						&& helper.getHelperSub().getId() == id)
					return helper;
			}
		}
		return null;
	}
	public Sprite getHelperWithID(String spriteId, int id) {
		Sprite spr = getSpriteInstance(spriteId);
		return getHelperWithID(spr, id);
	}
	public String getHelperIdWithID(Sprite sprite, int id) {
		Sprite helper = getHelperWithID(sprite, id);
		if (helper != null)
			return helper.getSpriteId();
		return null;
	}
	public String getHelperIdWithID(String spriteId, int id) {
		Sprite spr = getSpriteInstance(spriteId);
		return getHelperIdWithID(spr, id);
	}

// Ennemies
	public Sprite getFirstEnnmy(String spriteId) {
		Collection<Sprite> sprites = getEnnmies(getSpriteInstance(spriteId));
		for (Sprite sprite: sprites) {
			if (!(sprite instanceof SpriteHelper))
				return sprite;
		}
		assert false; // ca cas n'arrivera jamais
		return null;
	}
	
	public Collection<Sprite> getEnnmies(Sprite sprite) {
		if (sprite instanceof SpriteHelper)
			return getEnnmiesHelper((SpriteHelper) sprite);
		if (getTeamOne().containsKey(sprite.getSpriteId())) {
			return getTeamTwo().values();
		} else if (getTeamTwo().containsKey(sprite.getSpriteId())) {
			return getTeamOne().values();
		}
		return null;
	}
	private Collection<Sprite> getEnnmiesHelper(SpriteHelper sprite) {
		Sprite spr = getRoot(sprite);
		if (getTeamOne().containsKey(spr.getSpriteId())) {
			return getTeamTwo().values();
		} else if (getTeamTwo().containsKey(spr.getSpriteId())) {
			return getTeamOne().values();
		}
		
		return null;
	}
//	public Collection<Sprite> getEnnmies(String spriteId) {
//
//		
//		if (getTeamOne().containsKey(spriteId)) {
//			return getTeamTwo().values();
//		} else if (getTeamTwo().containsKey(spriteId)) {
//			return getTeamOne().values();
//		}
//		return null;
//	}
	public Collection<Sprite> getEnnmies(AbstractSprite spriteHitter) {
		if (spriteHitter instanceof Sprite) {
			return getEnnmies((Sprite)spriteHitter);
		} else if (spriteHitter instanceof ProjectileSprite) {
			return getEnnmies((ProjectileSprite)spriteHitter);
		}
		assert false;
		return null;
	}
	private Collection<Sprite> getEnnmies(ProjectileSprite projectile) {
		Sprite parent = projectile.getProjectileSub().getSpriteParent();
		return getEnnmies(parent);
	}
//	 Partners
	public Collection<Sprite> getPartners(Sprite sprite) {
		if (sprite == null)
			return Collections.EMPTY_LIST;
		if (sprite instanceof SpriteHelper)
			return getPartnersHelper((SpriteHelper) sprite);
		if (getTeamOne().containsKey(sprite.getSpriteId())) {
			return getTeamOne().values();
		} else if (getTeamTwo().containsKey(sprite.getSpriteId())) {
			return getTeamTwo().values();
		}
		return null;
	}

	public Collection<Sprite> getPartners(AbstractSprite spriteHitter) {
		if (spriteHitter instanceof Sprite) {
			return getPartners((Sprite)spriteHitter);
		} else if (spriteHitter instanceof ProjectileSprite) {
			return getPartners((ProjectileSprite)spriteHitter);
		}
		assert false;
		return null;
	}
	private Collection<Sprite> getPartners(ProjectileSprite projectile) {
		Sprite parent = projectile.getProjectileSub().getSpriteParent();
		return getPartners(parent);
	}
	private Collection<Sprite> getPartnersHelper(SpriteHelper sprite) {
		Sprite spr = getRoot(sprite);
		if (getTeamOne().containsKey(spr.getSpriteId())) {
			return getTeamTwo().values();
		} else if (getTeamTwo().containsKey(spr.getSpriteId())) {
			return getTeamOne().values();
		}
		
		return null;
	}
///
	private void addPartner(Sprite sprite, Sprite parent) {
		if (getTeamOne().containsKey(parent.getSpriteId())) {
			getTeamOne().put(sprite.getSpriteId(), sprite);
		} else if (getTeamTwo().containsKey(parent.getSpriteId())) {
			getTeamTwo().put(sprite.getSpriteId(), sprite);
		}
	}
	private void removePartner(Sprite sprite) {
		if (sprite != null) {
			getTeamOne().remove(sprite.getSpriteId());
			getTeamTwo().remove(sprite.getSpriteId());

		}
	}
	public boolean isHomeTeam(String spriteId) {
		return getTeamOne().containsKey(spriteId);
	}
	public void removeExplod(String spriteId, int id) {
		for (AbstractSprite s: _otherSprites) {
			if (s instanceof ExplodSprite) {
				ExplodSprite es = (ExplodSprite) s;
				if (es.getExplod().getId() == id && es.getExplod().getSprite().getSpriteId().equals(spriteId)) {
					es.setForceRemove(true);
//					return;
				}
			}
		}
		
	}
	public void removeAfterImageSprite(AfterImageSprite afterimageSprite) {
		if (afterimageSprite != null) {
			_otherSprites.remove(afterimageSprite);
			afterimageSprite.setRemove(true);
			
		}
	}
	public void addAfterImageSprite(AfterImageSprite afterimageSprite) {
		_otherSprites.add(afterimageSprite);
		addRender(new AfterimageRender(afterimageSprite));
	}
	public void setAfterImageTime(String spriteId, int itime) {
		for (AbstractSprite s : getOtherSprites()) {
			if (s instanceof AfterImageSprite) {
				AfterImageSprite spr = (AfterImageSprite) s;
				if (spr.getSprite().getSpriteId().equals(spriteId)) {
					spr.setNewTimeForAll(itime);
					spr.rezetCount();
				}
			}
		}
		
	}
	public void addMakedustSpriteManager(MakeDustSpriteManager manager) {
		_otherSprites.add(manager);
		MakedustRender render = new MakedustRender(manager);
		addRender(render);
		
	}

	public static int countNormalPlayer(Collection<Sprite> sprites) {
		int count = 0;
		for (Sprite s: sprites) {
			if (s instanceof SpriteHelper) {
				SpriteHelper helper = (SpriteHelper) s;
				if (helper.getHelperSub().getHelpertype().equals("player"))
					count++;
			} else {
				count++;
			}
		}
		
		return count;

	}
	public void removeExplod(String spriteId) {
		for (AbstractSprite s: _otherSprites) {
			if (s instanceof ExplodSprite) {
				ExplodSprite es = (ExplodSprite) s;
				if (es.getExplod().getSprite().getSpriteId().equals(spriteId)) {
					es.setForceRemove(true);
//					return;
				}
			}
		}
	}
	public Collection<ExplodSprite> getExplodeSprites(Integer id) {
		Collection<ExplodSprite> sprites = new ArrayList<ExplodSprite>();
		for (AbstractSprite s: getOtherSprites()) {
			if (s instanceof ExplodSprite && 
					(id.intValue() == -1
							|| ((ExplodSprite)s).getExplod().getId() == id.intValue()))
				sprites.add((ExplodSprite) s);
		}
		
		return sprites;
	}

	public void initRound() {
		initRound(1);
	}
	public void initRound(int round) {
		getFightdef().rezet();
		getStage().getCamera().init();
		
		for (AbstractSprite s: getOtherSprites()) {
			removeRender(s);
		}
		getOtherSprites().clear();
		
  		for (Sprite s: getSprites()) {
  			if (s instanceof SpriteHelper) {
  				_spriteHelperRemoveList.add(s.getSpriteId());
  				continue;
  			}

			_spriteHelperRemoveList.addAll(getHelpersIds(getSpriteInstance(s.getSpriteId())));
			helperManagement();
			if (getTeamOne().get(s.getSpriteId()) != null)
				s.getInfo().setXPos(getStage().getPlayerinfo().getP1startx());
			else
				s.getInfo().setXPos(getStage().getPlayerinfo().getP2startx());
			s.getInfo().setYPos(0);
			s.getSpriteState().clearVars();

		}
		helperManagement();

  		processPosition();
  		for (Sprite s: getSprites()) {
  			s.getSpriteState().setProcess(true);
  			s.getInfo().setCtrl(1);
			s.getSpriteState().selfstate(5900);
			s.getInfo().init();
  		}
		getGameState().init(stateMachine, round);
	}
	
	private void processPosition() {
		for (Sprite s: getSprites()) {
			if (s instanceof SpriteHelper)
				continue;
			Sprite one = s;
			Sprite two = FightEngine.getNearestEnnemies(one);
			if (one.getRealXPos() < two.getRealXPos()) {
				one.getInfo().setFlip(false);
				two.getInfo().setFlip(true);
			} else {
				one.getInfo().setFlip(true);
				two.getInfo().setFlip(false);
				
			}
		}
		
	}
	public void onDebugAction(DebugAction action) {
		switch (action) {
		case SWICTH_PLAYER_DEBUG_INFO:
			DebugRender.debugRender.nextSprite();
			break;
		case INIT_PLAYER:
			initRound();
			break;
		case SHOW_HIDE_CNS:
			for (CnsRender cnsRender: cnsRenderList) {
				cnsRender.setShowCns(!cnsRender.isShowCns());
			}
			break;
		case SHOW_HIDE_ATTACK_CNS:
			for (CnsRender cnsRender: cnsRenderList) {
				cnsRender.setShowAttackCns(!cnsRender.isShowAttackCns());
			}
			break;
		case INCREASE_FPS:
			long famerate = getWindow().getTimer().getFramerate();
			getWindow().getTimer().setFramerate(famerate + 1);
			break;
		case DECREASE_FPS:
			long famerate2 = getWindow().getTimer().getFramerate();
			getWindow().getTimer().setFramerate(famerate2 - 1 < 0? 0: famerate2 - 1);
			break;
		case RESET_FPS:
			getWindow().getTimer().setFramerate(MugenTimer.DEFAULT_FPS);
			break;
		case DISPLAY_HELP:
			DebugRender.debugRender.setDisplayHelp(!DebugRender.debugRender.isDisplayHelp());
			break;
		case DEBUG_PAUSE:
			getGlobalEvents().pauseUnpause();

			break;
		case EXPLOD_DEBUG_INFO:
			DebugExplodRender.debugRender.setDisplay(!DebugExplodRender.debugRender.isDisplay());
			break;
		case PAUSE_PLUS_ONE_FRAME:
			if (getGlobalEvents().isSystemPause()) {
				try {
					getGlobalEvents().setForceUpdate(true);
					update(1);
					getGlobalEvents().setForceUpdate(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		
		default:
			break;
		}
		
	}
	private Collection<? extends String> getHelpersIds(Sprite sprite) {
		LinkedList<String> list = new LinkedList<String>();
		for (Sprite s: getPartners(sprite)) {
			if (!s.equals(sprite) && (s instanceof SpriteHelper)) {
				SpriteHelper helper = (SpriteHelper) s;
				if (helper.getHelperSub().getSpriteFrom().equals(sprite))
					list.add(helper.getSpriteId());
			}
		}
		return list;
	}
	public void renderDebugInfo() {
		DebugRender.debugRender.render();
		DebugExplodRender.debugRender.render();
	}

	
	
	
	
	String loadingText = "";
	long aTime = System.currentTimeMillis();
	public void displayPendingScreeen() {
		try {
			if (System.currentTimeMillis() - aTime > 1000 
					&& loadingText != null && loadingText.length() > 0) {
			
				loadingText += ".";
				aTime = System.currentTimeMillis();
			}
			String[] strSpriteInfos = loadingText.split("\n");
			FontProducer fp = FontParser.getFontProducer();
			int addX = 0;
			int x = 10;
			int y = 10;
			for (String s: strSpriteInfos) {
				fp.draw(0, x, y+=fp.getSize().height, GraphicsWrapper.getInstance(), s);
				addX = Math.max(addX, s.length());
			}
			aTime++;
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	Game next;
	@Override
	public Game getNext() throws Exception {
		return next == null? this: next;
	}
	@Override
	public void reInit(GameWindow container) throws Exception {
		// TODO Auto-generated method stub
		
	}
	public void preloadSprite(int teamside, String spriteId, String sprDef,
			int pal) throws Exception {
		SpriteDef def = SpriteDef.parseSpriteDef(sprDef);
		
		preloadSprite(teamside, spriteId, def, pal);
		
	}
	@Override
	public void free() {
		for (Sprite spr: getSprites()) {
			Logger.log("Free " + spr.getSpriteId());
			spr.getSpriteSFF().free();
		}
		for (AbstractSprite spr: getOtherSprites()) {
			if (spr.getSpriteSFF() != null) {
				Logger.log("Free " + spr);
				spr.getSpriteSFF().free();
			}
		}
//		if (getStage() != null)
//			getStage().free();
	}


}
