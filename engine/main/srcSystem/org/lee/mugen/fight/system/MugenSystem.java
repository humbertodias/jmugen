package org.lee.mugen.fight.system;

import java.io.File;
import java.io.Reader;
import java.util.List;

import org.lee.mugen.background.Background;
import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.io.MugenDataStreams;
import org.lee.mugen.fight.section.Section;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;

public class MugenSystem {
	private MugenSystem(String filename) {
		this.filename = filename;
	}
	private static MugenSystem instance;

	public static final synchronized MugenSystem getInstance() {
		if (instance == null) {
			instance = new MugenSystem(JMugenConstant.RESOURCE + "data/system.def");
			try {
				instance.parse();
			} catch (Exception e) {
				e.printStackTrace();
				throw new IllegalStateException();
			}
		}
		return instance;
	}
	public SpriteSFF getSpriteSff() {
		return getFiles().getSpr();
	}
	
	public File getCurrentDir() {
		int slash = Math.max(filename.lastIndexOf('/'), filename.lastIndexOf('\\'));
		if (slash < 0) {
			return new File("");
		}
		return new File(filename.substring(0, slash));
	}

	private Info info;
	private Files files;
	private Music music;

	private TitleInfo titleInfo;
	private Background titleBackground;

	private SelectInfo selectInfo;
	private Background selectBackground;

	private VsScreen vsScreen;
	private Background versusBackground;

	private DemoMode demoMode;

	private ContinueScreen continueScreen;

	private GameOverScreen gameOverScreen;

	private WinScreen winScreen;

	private DefaultEnding defaultEnding;

	private EndCredits endCredits;

	private SurvivalResultsScreen survivalResultsScreen;

	private OptionInfo optionInfo;
	private Background optionBackground;

	private String filename;

	private void parse(Section section, GroupText grp) throws Exception {
		for (String key: grp.getKeysOrdered()) {
			String value = grp.getKeyValues().get(key);
			section.parse(this, key, value);
		}
	}

	public Background buildBackground(String prefix) {
		String bgdefRegex = " *" + prefix + "bgdef *";
		String bgRegex = " *" + prefix + "bg +" + "(.*)\\s*|" + prefix + "bg";
		String bgctrldefRegex = " *" + prefix + "bgctrldef +" + "(.*) *";
	    String bgCtrlRegex = " *" + prefix + "bgctrl +" + "([a-zA-Z0-9\\.\\ \\-\\_]*) *";
	    
		return new Background(this, getCurrentDir(), bgdefRegex, bgRegex, bgctrldefRegex, bgCtrlRegex);
	}
	
	private void parse() throws Exception {
        Reader r = MugenDataStreams.openUtf8Reader(filename);

		List<GroupText> groups = Parser.getGroupTextMap(r);

		titleBackground = buildBackground("title");
		titleBackground.parse(this, groups);
		
		selectBackground = buildBackground("select");
		selectBackground.parse(this, groups);
		
		versusBackground = buildBackground("versus");
		versusBackground.parse(this, groups);
		
		optionBackground = buildBackground("option");
		optionBackground.parse(this, groups);
		
		for (GroupText grp : groups) {
			if (grp.getSection().equals("info")) {
				info = new Info();
				parse(info, grp);
			} else if (grp.getSection().equals("files")) {
				files = new Files();
				parse(files, grp);
			} else if (grp.getSection().equals("music")) {
				music = new Music();
				parse(music, grp);
			} else if (grp.getSection().equals("title info")) {
				titleInfo = new TitleInfo();
				parse(titleInfo, grp);
			} else if (grp.getSection().equals("select info")) {
				selectInfo = new SelectInfo();
				parse(selectInfo, grp);
			} else if (grp.getSection().equals("vs screen")) {
				vsScreen = new VsScreen();
				parse(vsScreen, grp);
			} else if (grp.getSection().equals("demo mode")) {
				demoMode = new DemoMode();
				parse(demoMode, grp);
			} else if (grp.getSection().equals("continue screen")) {
				continueScreen = new ContinueScreen();
				parse(continueScreen, grp);
			} else if (grp.getSection().equals("game over screen")) {
				gameOverScreen = new GameOverScreen();
				parse(gameOverScreen, grp);
			} else if (grp.getSection().equals("win screen")) {
				winScreen = new WinScreen();
				parse(winScreen, grp);
			} else if (grp.getSection().equals("default ending")) {
				defaultEnding = new DefaultEnding();
				parse(defaultEnding, grp);
			} else if (grp.getSection().equals("end credits")) {
				endCredits = new EndCredits();
				parse(endCredits, grp);
			} else if (grp.getSection().equals("survival results screen")) {
				survivalResultsScreen = new SurvivalResultsScreen();
				parse(survivalResultsScreen, grp);
			} else if (grp.getSection().equals("option info")) {
				optionInfo = new OptionInfo();
				parse(optionInfo, grp);
			}
		}

	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Files getFiles() {
		return files;
	}

	public void setFiles(Files files) {
		this.files = files;
	}

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public TitleInfo getTitleInfo() {
		return titleInfo;
	}

	public void setTitleInfo(TitleInfo titleInfo) {
		this.titleInfo = titleInfo;
	}

	public Background getTitleBackground() {
		return titleBackground;
	}

	public void setTitleBackground(Background titleBackground) {
		this.titleBackground = titleBackground;
	}

	public SelectInfo getSelectInfo() {
		return selectInfo;
	}

	public void setSelectInfo(SelectInfo selectInfo) {
		this.selectInfo = selectInfo;
	}

	public Background getSelectBackground() {
		return selectBackground;
	}

	public void setSelectBackground(Background selectBackground) {
		this.selectBackground = selectBackground;
	}

	public VsScreen getVsScreen() {
		return vsScreen;
	}

	public void setVsScreen(VsScreen vsScreen) {
		this.vsScreen = vsScreen;
	}

	public Background getVersusBackground() {
		return versusBackground;
	}

	public void setVersusBackground(Background versusBackground) {
		this.versusBackground = versusBackground;
	}

	public DemoMode getDemoMode() {
		return demoMode;
	}

	public void setDemoMode(DemoMode demoMode) {
		this.demoMode = demoMode;
	}

	public ContinueScreen getContinueScreen() {
		return continueScreen;
	}

	public void setContinueScreen(ContinueScreen continueScreen) {
		this.continueScreen = continueScreen;
	}

	public GameOverScreen getGameOverScreen() {
		return gameOverScreen;
	}

	public void setGameOverScreen(GameOverScreen gameOverScreen) {
		this.gameOverScreen = gameOverScreen;
	}

	public WinScreen getWinScreen() {
		return winScreen;
	}

	public void setWinScreen(WinScreen winScreen) {
		this.winScreen = winScreen;
	}

	public DefaultEnding getDefaultEnding() {
		return defaultEnding;
	}

	public void setDefaultEnding(DefaultEnding defaultEnding) {
		this.defaultEnding = defaultEnding;
	}

	public EndCredits getEndCredits() {
		return endCredits;
	}

	public void setEndCredits(EndCredits endCredits) {
		this.endCredits = endCredits;
	}

	public SurvivalResultsScreen getSurvivalResultsScreen() {
		return survivalResultsScreen;
	}

	public void setSurvivalResultsScreen(SurvivalResultsScreen survivalResultsScreen) {
		this.survivalResultsScreen = survivalResultsScreen;
	}

	public OptionInfo getOptionInfo() {
		return optionInfo;
	}

	public void setOptionInfo(OptionInfo optionInfo) {
		this.optionInfo = optionInfo;
	}

	public Background getOptionBackground() {
		return optionBackground;
	}

	public void setOptionBackground(Background optionBackground) {
		this.optionBackground = optionBackground;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
