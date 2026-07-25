package org.lee.mugen.core.gameSelect;

import java.io.Reader;
import java.util.List;
import org.lee.mugen.background.Background;
import org.lee.mugen.background.GwtStageRoot;
import org.lee.mugen.background.GwtStageSelectBackground;
import org.lee.mugen.core.Game;
import org.lee.mugen.core.GameMenu;
import org.lee.mugen.core.GwtGameFight;
import org.lee.mugen.core.JMugenConstant;
import org.lee.mugen.core.renderer.game.select.GwtSelectRender;
import org.lee.mugen.core.sound.SoundSystem;
import org.lee.mugen.fight.select.GwtSelect;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.fight.system.SelectInfo;
import org.lee.mugen.geom.MugenPoint;
import org.lee.mugen.io.MugenDataStreams;
import org.lee.mugen.renderer.GameWindow;
import org.lee.mugen.sprite.parser.Parser;
import org.lee.mugen.sprite.parser.Parser.GroupText;

/**
 * GWT character select: two players, stage browse and confirm.
 */
public class GwtGameSelect implements Game {

    private static final GwtGameSelect INSTANCE = new GwtGameSelect();

    public static GwtGameSelect getInstance() {
        return INSTANCE;
    }

    private GwtSelectRender selectRender;
    private GwtSelectionController[] selectionControllers;
    private Background stagePreviewBackground;
    private int loadedStageIndex = -1;
    private boolean stageChanged;
    private Game next;
    private boolean backToMenu;
    private boolean versusLaunched;

    private GwtGameSelect() {
    }

    @Override
    public void free() {
        stagePreviewBackground = null;
        loadedStageIndex = -1;
    }

    @Override
    public Game getNext() throws Exception {
        return next == null ? this : next;
    }

    @Override
    public void init(GameWindow container) throws Exception {
        MugenSystem ms = MugenSystem.getInstance();
        SoundSystem.SoundBackGround.stopMusic();
        String bgm = ms.getMusic().getSelect$bgm();
        if (bgm != null && bgm.length() > 0) {
            SoundSystem.SoundBackGround.playMusic(JMugenConstant.RESOURCE + bgm);
        }

        next = null;
        backToMenu = false;
        versusLaunched = false;
        stagePreviewBackground = null;
        loadedStageIndex = -1;
        stageChanged = true;
        selectRender = new GwtSelectRender(this);
        initBackgrounds(ms.getSelectBackground());

        GwtSelectionController.initShareVariable();
        SelectInfo info = ms.getSelectInfo();
        MugenPoint p1Start = new MugenPoint(0, 0);
        MugenPoint p2Start = new MugenPoint(0, 4);
        if (info != null) {
            if (info.getPlayer1() != null
                    && info.getPlayer1().getCursor() != null
                    && info.getPlayer1().getCursor().getStartcell() != null) {
                p1Start = info.getPlayer1().getCursor().getStartcell();
            }
            if (info.getPlayer2() != null
                    && info.getPlayer2().getCursor() != null
                    && info.getPlayer2().getCursor().getStartcell() != null) {
                p2Start = info.getPlayer2().getCursor().getStartcell();
            }
        }
        selectionControllers =
                new GwtSelectionController[] {
                    new GwtSelectionController(this, "1", p1Start),
                    new GwtSelectionController(this, "2", p2Start)
                };

        container.clearListener();
        for (GwtSelectionController sc : selectionControllers) {
            container.addActionListener(sc);
        }
    }

    public GwtSelectionController[] getSelectionControllers() {
        return selectionControllers;
    }

    public String getSelectedSprite(MugenPoint p) {
        SelectInfo info = MugenSystem.getInstance().getSelectInfo();
        GwtSelect select = MugenSystem.getInstance().getFiles().getGwtSelect();
        if (info == null || select == null || p == null) {
            return null;
        }
        return select.getCharacterAt(p.x, p.y, info.getRows(), info.getColumns());
    }

    public String getCurrentStagePath() {
        GwtSelect select = MugenSystem.getInstance().getFiles().getGwtSelect();
        if (select == null || select.getExtraStages().getStages().isEmpty()) {
            return null;
        }
        int idx = GwtSelectionController.getShareIndexOfStage();
        List<String> stages = select.getExtraStages().getStages();
        if (idx < 0 || idx >= stages.size()) {
            idx = 0;
        }
        return stages.get(idx);
    }

    public String getCurrentStageDisplayName() {
        String path = getCurrentStagePath();
        if (path == null) {
            return "";
        }
        return MugenSystem.getInstance().getFiles().getGwtSelect().getExtraStages().getRealName(path);
    }

    public Background getStagePreviewBackground() {
        if (!GwtSelectionController.isBothCharsSelected() || GwtSelectionController.isStageSelected()) {
            return null;
        }
        ensureStagePreviewLoaded();
        return stagePreviewBackground;
    }

    public void setStageChanged() {
        stageChanged = true;
    }

    public void setBackToMenu() {
        backToMenu = true;
    }

    public String getSelectedCharacterName(int playerIndex) {
        if (selectionControllers == null || playerIndex >= selectionControllers.length) {
            return null;
        }
        return getSelectedSprite(selectionControllers[playerIndex].getPosition());
    }

    /** Same contract as desktop {@link org.lee.mugen.core.gameSelect.GameSelect#getGameFight()}. */
    public Game getGameFight() throws Exception {
        String p1 = getSelectedCharacterName(0);
        String p2 = getSelectedCharacterName(1);
        String stage = getCurrentStagePath();
        return new GwtGameFight(stage, p1 != null ? p1 : "p1", p2 != null ? p2 : "p2");
    }

    private void ensureStagePreviewLoaded() {
        int idx = GwtSelectionController.getShareIndexOfStage();
        if (!stageChanged && loadedStageIndex == idx && stagePreviewBackground != null) {
            return;
        }
        stageChanged = false;
        loadedStageIndex = idx;
        stagePreviewBackground = null;

        String path = getCurrentStagePath();
        if (path == null) {
            return;
        }
        try {
            String resourcePath = JMugenConstant.RESOURCE + path;
            GwtStageRoot root = new GwtStageRoot(resourcePath);
            Reader reader = MugenDataStreams.openUtf8Reader(resourcePath);
            List<GroupText> groups;
            try {
                groups = Parser.getGroupTextMap(reader);
            } finally {
                reader.close();
            }
            GwtStageSelectBackground stageBg = new GwtStageSelectBackground(root);
            stageBg.parse(root, groups);
            initBackgrounds(stageBg);
            stagePreviewBackground = stageBg;
        } catch (Exception ignored) {
            stagePreviewBackground = null;
        }
    }

    private static void initBackgrounds(Background background) {
        if (background == null) {
            return;
        }
        for (org.lee.mugen.background.BG bg : background.getBgs()) {
            if (!bg.isInit()) {
                bg.init();
                bg.setInit(true);
            }
        }
    }

    @Override
    public void reInit(GameWindow container) throws Exception {
        backToMenu = false;
        versusLaunched = false;
        next = null;
        stagePreviewBackground = null;
        loadedStageIndex = -1;
    }

    @Override
    public void render() throws Exception {
        if (selectRender != null && selectionControllers != null) {
            selectRender.setControllers(selectionControllers);
            selectRender.render();
        }
    }

    @Override
    public void update(int delta) throws Exception {
        if (backToMenu) {
            next = GameMenu.getInstance();
            return;
        }
        Background bg = MugenSystem.getInstance().getSelectBackground();
        if (bg != null) {
            bg.process();
        }
        if (GwtSelectionController.isBothCharsSelected() && !GwtSelectionController.isStageSelected()) {
            ensureStagePreviewLoaded();
            if (stagePreviewBackground != null) {
                stagePreviewBackground.process();
            }
        }
        if (selectionControllers != null
                && GwtSelectionController.isAllSelectionDone(selectionControllers.length)
                && !versusLaunched) {
            versusLaunched = true;
            next = new GwtGameVsScreen(this);
        }
    }
}
