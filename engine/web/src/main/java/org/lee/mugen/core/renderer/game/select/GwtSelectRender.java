package org.lee.mugen.core.renderer.game.select;

import java.util.Iterator;
import org.lee.mugen.core.gameSelect.GwtGameSelect;
import org.lee.mugen.core.gameSelect.GwtSelectionController;
import org.lee.mugen.background.Background;
import org.lee.mugen.core.renderer.game.GwtStageBackgroundRender;
import org.lee.mugen.core.renderer.game.fight.BaseRender;
import org.lee.mugen.core.renderer.game.system.BackgroundRender;
import org.lee.mugen.fight.section.elem.FontType;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.fight.select.GwtCharacters;
import org.lee.mugen.fight.select.GwtSelect;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.fight.system.SelectInfo;
import org.lee.mugen.fight.system.elem.Cell;
import org.lee.mugen.fight.system.elem.PlayerSelectInfo;
import org.lee.mugen.fight.system.elem.StageDisplay;
import org.lee.mugen.geom.MugenPoint;
import org.lee.mugen.object.Rectangle;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.sprite.entity.PointF;

/**
 * GWT character-select draw: grid, portraits, cursors, stage preview and name.
 */
public class GwtSelectRender extends BaseRender {

    private final BackgroundRender backgroundRender;
    private final GwtGameSelect gameSelect;
    private GwtSelectionController[] controllers = new GwtSelectionController[0];

    public GwtSelectRender(GwtGameSelect gameSelect) {
        this.gameSelect = gameSelect;
        backgroundRender = new BackgroundRender(MugenSystem.getInstance().getSelectBackground());
    }

    public void setControllers(GwtSelectionController[] controllers) {
        this.controllers = controllers != null ? controllers : new GwtSelectionController[0];
    }

    @Override
    public void render() {
        MugenDrawer md = GraphicsWrapper.getInstance();
        MugenSystem ms = MugenSystem.getInstance();
        SelectInfo selectInfo = ms.getSelectInfo();
        if (selectInfo == null) {
            return;
        }

        RGB rgb = new RGB(1, 1, 1);
        md.setColor(rgb.getR(), rgb.getG(), rgb.getB(), rgb.getA());
        md.fillRect(0, 0, 320, 240);
        backgroundRender.render();
        md.setAlpha(1);

        GwtSelect gwtSelect = ms.getFiles().getGwtSelect();
        if (gwtSelect == null) {
            return;
        }
        GwtCharacters characters = gwtSelect.getCharacters();
        int row = selectInfo.getRows();
        int col = selectInfo.getColumns();
        Cell cell = selectInfo.getCell();
        if (cell == null || selectInfo.getPos() == null || cell.getSize() == null) {
            return;
        }
        MugenPoint cellSize = cell.getSize();
        int spacing = cell.getSpacing();
        MugenPoint gridPos = selectInfo.getPos();

        for (int c = 0; c < col; c++) {
            for (int r = 0; r < row; r++) {
                MugenPoint p = cellPos(gridPos, cellSize, spacing, r, c);
                if (cell.getBg() != null) {
                    render(md, p, cell.getBg());
                }
            }
        }

        Iterator<String> iterCharacter = gwtSelect.getCharactersOrder().iterator();
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                MugenPoint p = cellPos(gridPos, cellSize, spacing, r, c);
                if (iterCharacter.hasNext()) {
                    String character = iterCharacter.next();
                    ImageContainer portrait = characters.getPortrait(character);
                    if (portrait != null) {
                        drawImage(md, p, portrait, false, false, 1f, 1f);
                    }
                }
            }
        }

        for (GwtSelectionController sc : controllers) {
            PlayerSelectInfo player = sc.getPlayerInfo();
            if (player == null || player.getCursor() == null) {
                continue;
            }
            MugenPoint gridCell = sc.getPosition();
            MugenPoint p = cellPos(gridPos, cellSize, spacing, gridCell.x, gridCell.y);
            Type cursor =
                    sc.isCharSelected()
                            ? player.getCursor().getDone()
                            : player.getCursor().getActive();
            if (cursor != null) {
                render(md, p, cursor);
            }
        }

        for (GwtSelectionController sc : controllers) {
            String characterId =
                    gwtSelect.getCharacterAt(
                            sc.getPosition().x,
                            sc.getPosition().y,
                            row,
                            col);
            if (characterId != null) {
                drawBigPortrait(md, characters, characterId, sc.getPlayerInfo());
                drawName(md, characters, characterId, sc.getPlayerInfo());
            }
        }

        if (GwtSelectionController.isBothCharsSelected()) {
            renderStageSelect(md, selectInfo);
        }
    }

    private void renderStageSelect(MugenDrawer md, SelectInfo selectInfo) {
        if (selectInfo.getStage() == null) {
            return;
        }

        StageDisplay stageDisplay = selectInfo.getStagedisplay();
        Background stageBg = gameSelect.getStagePreviewBackground();
        if (stageDisplay != null
                && stageDisplay.isEnable()
                && stageBg != null
                && stageDisplay.getRectangle() != null
                && stageDisplay.getScale() != null
                && stageDisplay.getPos() != null) {
            int width = stageDisplay.getRectangle().width;
            int height = stageDisplay.getRectangle().height;
            float xCoef = stageDisplay.getScale().getX();
            float yCoef = stageDisplay.getScale().getY();
            int x = stageDisplay.getPos().x;
            int y = stageDisplay.getPos().y;
            Rectangle clip =
                    new Rectangle(x, y, x + (int) (width * xCoef), y + (int) (height * yCoef));
            MugenPoint camera = stageDisplay.getCamera();
            int xStart = camera != null ? (int) camera.x : 0;
            float camY = camera != null ? camera.y : 0f;
            md.setClip(clip);
            md.setUniformScaleAboutClipCorner(true);
            md.scale(xCoef, yCoef);
            new GwtStageBackgroundRender(stageBg, 0f, camY, xStart).render();
            md.scale(1f / xCoef, 1f / yCoef);
            md.setClip(null);
            md.setUniformScaleAboutClipCorner(false);
        }

        if (selectInfo.getStage().getDone() != null) {
            Type done = selectInfo.getStage().getDone().copy();
            if (done.getType() instanceof FontType) {
                FontType font = (FontType) done.getType();
                font.setText(gameSelect.getCurrentStageDisplayName());
                render(md, selectInfo.getStage().getPos(), done);
            }
        }
    }

    private static MugenPoint cellPos(
            MugenPoint gridPos, MugenPoint cellSize, int spacing, int r, int c) {
        return new MugenPoint(
                gridPos.x + c * (cellSize.x + spacing), gridPos.y + r * (cellSize.y + spacing));
    }

    private void drawBigPortrait(
            MugenDrawer md,
            GwtCharacters characters,
            String characterId,
            PlayerSelectInfo player) {
        if (player == null || player.getFace() == null) {
            return;
        }
        ImageContainer big = characters.getBigPortrait(characterId);
        if (big == null) {
            return;
        }
        Type face = player.getFace();
        MugenPoint pos = face.getOffset() != null ? face.getOffset().copy() : new MugenPoint();
        boolean facing = face.getFacing() == -1;
        PointF scale = face.getScale() != null ? face.getScale() : new PointF(1, 1);
        if (facing) {
            pos.x -= big.getWidth() * scale.getX();
        }
        drawImage(md, pos, big, facing, false, scale.getX(), scale.getY());
    }

    private void drawName(
            MugenDrawer md,
            GwtCharacters characters,
            String characterId,
            PlayerSelectInfo player) {
        if (player == null || player.getName() == null) {
            return;
        }
        Type nameType = player.getName().copy();
        if (!(nameType.getType() instanceof FontType)) {
            return;
        }
        FontType font = (FontType) nameType.getType();
        if (font.getFontno() < 0) {
            return;
        }
        font.setText(characters.getDisplayName(characterId));
        render(md, null, nameType);
    }

    private static void drawImage(
            MugenDrawer md,
            MugenPoint p,
            ImageContainer ic,
            boolean flipH,
            boolean flipV,
            float scaleX,
            float scaleY) {
        DrawProperties dp = new DrawProperties(p.x, p.y, flipH, flipV, ic);
        dp.setXScaleFactor(scaleX);
        dp.setYScaleFactor(scaleY);
        md.draw(dp);
    }
}
