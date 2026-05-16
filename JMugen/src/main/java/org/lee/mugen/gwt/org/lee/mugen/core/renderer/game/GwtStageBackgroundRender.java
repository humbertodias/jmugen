package org.lee.mugen.core.renderer.game;

import org.lee.mugen.background.BG;
import org.lee.mugen.background.Background;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.renderer.Trans;
import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;

/**
 * GWT stage background draw (like {@link StageBackgroundRender}) with camera offset for select preview.
 */
public class GwtStageBackgroundRender {

    private final Background background;
    private final float cameraX;
    private final float cameraY;
    private final int xStartForAll;
    private final float stageScaleX;
    private final float stageScaleY;
    private final int layerDisplay;

    public GwtStageBackgroundRender(Background background, float cameraX, float cameraY) {
        this(background, cameraX, cameraY, 0, 1f, 1f, 0);
    }

    public GwtStageBackgroundRender(
            Background background,
            float cameraX,
            float cameraY,
            int xStartForAll) {
        this(background, cameraX, cameraY, xStartForAll, 1f, 1f, 0);
    }

    public GwtStageBackgroundRender(
            Background background,
            float cameraX,
            float cameraY,
            float stageScaleX,
            float stageScaleY) {
        this(background, cameraX, cameraY, 0, stageScaleX, stageScaleY, 0);
    }

    public GwtStageBackgroundRender(
            Background background,
            float cameraX,
            float cameraY,
            float stageScaleX,
            float stageScaleY,
            int layerDisplay) {
        this(background, cameraX, cameraY, 0, stageScaleX, stageScaleY, layerDisplay);
    }

    public GwtStageBackgroundRender(
            Background background,
            float cameraX,
            float cameraY,
            int xStartForAll,
            float stageScaleX,
            float stageScaleY,
            int layerDisplay) {
        this.background = background;
        this.cameraX = cameraX;
        this.cameraY = cameraY;
        this.xStartForAll = xStartForAll;
        this.stageScaleX = stageScaleX;
        this.stageScaleY = stageScaleY;
        this.layerDisplay = layerDisplay;
    }

    public void render() {
        if (background == null) {
            return;
        }
        MugenDrawer md = GraphicsWrapper.getInstance();
        SpriteSFF sffSprite = background.getBgdef().getSpr();
        if (sffSprite == null) {
            sffSprite = MugenSystem.getInstance().getFiles().getSpr();
        }
        if (sffSprite == null) {
            return;
        }

        md.scale(stageScaleX, stageScaleY);
        float moveX = cameraX;
        float moveY = cameraY;

        for (BG bg : background.getBgs()) {
            if (bg.getLayerno() != layerDisplay) {
                continue;
            }
            try {
                if (bg.getBgType() == BG.Type.NORMAL || bg.getBgType() == BG.Type.NORM) {
                    drawNormal(md, sffSprite, bg, moveX, moveY, xStartForAll);
                } else if (bg.getBgType() == BG.Type.PARALLAX) {
                    drawParallax(md, sffSprite, bg, moveX, moveY, xStartForAll);
                }
            } catch (Exception ignored) {
            }
        }
        md.scale(1f / stageScaleX, 1f / stageScaleY);
    }

    private static void drawNormal(
            MugenDrawer md,
            SpriteSFF sffSprite,
            BG bg,
            float moveX,
            float moveY,
            int xStartForAll) {
        int grpno = bg.getSpriteno().getSpritegrp();
        int imgno = bg.getSpriteno().getSpriteno();
        if (sffSprite.getGroupSpr(grpno) == null) {
            return;
        }
        ImageSpriteSFF imgSprSff = sffSprite.getGroupSpr(grpno).getImgSpr(imgno);
        if (imgSprSff == null) {
            return;
        }
        ImageContainer img = (ImageContainer) imgSprSff.getImage();
        float x = bg.getPos().getX() - imgSprSff.getXAxis();
        float y = bg.getPos().getY() - imgSprSff.getYAxis();
        drawTileXY(md, img, bg, x, y, moveX, moveY, xStartForAll, bg.getBgTrans(), false, false);
    }

    private static void drawParallax(
            MugenDrawer md,
            SpriteSFF sffSprite,
            BG bg,
            float moveX,
            float moveY,
            int xStartForAll) {
        int grpno = bg.getSpriteno().getSpritegrp();
        int imgno = bg.getSpriteno().getSpriteno();
        if (sffSprite.getGroupSpr(grpno) == null) {
            return;
        }
        ImageSpriteSFF imgSprSff = sffSprite.getGroupSpr(grpno).getImgSpr(imgno);
        if (imgSprSff == null) {
            return;
        }
        ImageContainer img = (ImageContainer) imgSprSff.getImage();
        float x = bg.getPos().getX() - imgSprSff.getXAxis();
        float y = bg.getPos().getY() - imgSprSff.getYAxis();
        for (int v = 0; v < img.getHeight(); ++v) {
            float deltaY =
                    bg.getXscale().getX()
                            + (v
                                    * ((bg.getXscale().getY() - bg.getXscale().getX())
                                            / img.getHeight()));
            deltaY = deltaY * bg.getDelta().getX();
            float x1 = x + deltaY * moveX + xStartForAll;
            float y2 = y + moveY * bg.getDelta().getY() + v;
            if (!bg.isEnable()) {
                continue;
            }
            drawImageRegion(
                    md,
                    bg.getTrans(),
                    img,
                    x1,
                    y2,
                    x1 + img.getWidth(),
                    y2 + 1,
                    0,
                    v,
                    img.getWidth(),
                    v + 1);
        }
    }

    private static void drawTileXY(
            MugenDrawer md,
            ImageContainer img,
            BG bg,
            float x,
            float y,
            float moveX,
            float moveY,
            int xStartForAll,
            Trans trans,
            boolean flipH,
            boolean flipV) {
        if (!bg.isEnable()) {
            return;
        }
        float tileSpacingY =
                bg.getTilespacing() == null
                        ? img.getHeight()
                        : bg.getTilespacing().getY() == 0 ? img.getHeight() : bg.getTilespacing().getY();
        float deltaY = bg.getDelta().getY();
        float deltaX = bg.getDelta().getX();
        float startPosX = x + moveX * deltaX + xStartForAll;
        float yDraw = y + moveY * deltaY;
        if (bg.getTile().getY() > 0) {
            yDraw = (y + moveY * deltaY) % tileSpacingY;
        }
        drawImage(md, trans, img, startPosX, yDraw, flipH, flipV);
    }

    private static void drawImage(
            MugenDrawer md, Trans trans, ImageContainer img, float x, float y, boolean flipH, boolean flipV) {
        DrawProperties dp = new DrawProperties(x, y, flipH, flipV, img);
        md.draw(dp);
    }

    private static void drawImageRegion(
            MugenDrawer md,
            Trans trans,
            ImageContainer img,
            float x1,
            float y1,
            float x2,
            float y2,
            int srcX1,
            int srcY1,
            int srcX2,
            int srcY2) {
        DrawProperties dp = new DrawProperties(x1, y1, x2, y2, srcX1, srcY1, srcX2, srcY2, false, false, img);
        md.draw(dp);
    }
}
