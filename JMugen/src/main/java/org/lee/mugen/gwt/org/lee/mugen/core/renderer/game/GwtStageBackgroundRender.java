package org.lee.mugen.core.renderer.game;

import org.lee.mugen.background.BG;
import org.lee.mugen.background.Background;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.parser.air.AirData;
import org.lee.mugen.parser.air.AirData.TypeBlit;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.renderer.RGB;
import org.lee.mugen.renderer.Trans;
import org.lee.mugen.sprite.base.AbstractAnimManager;
import org.lee.mugen.sprite.baseForParse.ImageSpriteSFF;
import org.lee.mugen.sprite.baseForParse.SpriteSFF;

/**
 * GWT stage background draw (like {@link StageBackgroundRender}) with camera offset.
 * Proper X tiling + screen-center offset so the stage does not leave black gaps when scrolling.
 */
public class GwtStageBackgroundRender {

    private static final int SCREEN_W = 320;
    private static final int SCREEN_H = 240;

    private final Background background;
    private final float cameraX;
    private final float cameraY;
    private final int xStartForAll;
    private final float stageScaleX;
    private final float stageScaleY;
    private final int layerDisplay;

    public GwtStageBackgroundRender(Background background, float cameraX, float cameraY) {
        this(background, cameraX, cameraY, SCREEN_W / 2, 1f, 1f, 0);
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
        this(background, cameraX, cameraY, SCREEN_W / 2, stageScaleX, stageScaleY, 0);
    }

    public GwtStageBackgroundRender(
            Background background,
            float cameraX,
            float cameraY,
            float stageScaleX,
            float stageScaleY,
            int layerDisplay) {
        this(background, cameraX, cameraY, SCREEN_W / 2, stageScaleX, stageScaleY, layerDisplay);
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

        // Only clear when an explicit colour was parsed (default RGB is white 255,255,255
        // and would wipe the stage if sprites fail / lag a frame).
        RGB clear = background.getBgdef().getBgclearcolor();
        if (clear != null && (clear.getR() != 255f || clear.getG() != 255f || clear.getB() != 255f)) {
            md.setColor(clear.getR() / 255f, clear.getG() / 255f, clear.getB() / 255f);
            md.fillRect(0, 0, SCREEN_W, SCREEN_H);
            md.setColor(1f, 1f, 1f, 1f);
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
                } else if (bg.getBgType() == BG.Type.ANIM) {
                    drawAnim(md, sffSprite, bg, moveX, moveY, xStartForAll);
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

    private static void drawAnim(
            MugenDrawer md,
            SpriteSFF sffSprite,
            BG bg,
            float moveX,
            float moveY,
            int xStartForAll) {
        AbstractAnimManager animMng = bg.getAnimManager();
        if (animMng == null || animMng.getCurrentImageSprite() == null) {
            return;
        }
        AirData air = animMng.getCurrentImageSprite().getAirData();
        int grpno = air.getGrpNum();
        int imgno = air.getImgNum();
        if (sffSprite.getGroupSpr(grpno) == null) {
            return;
        }
        ImageSpriteSFF imgSprSff = sffSprite.getGroupSpr(grpno).getImgSpr(imgno);
        if (imgSprSff == null) {
            return;
        }
        ImageContainer img = (ImageContainer) imgSprSff.getImage();
        float x = bg.getPos().getX() - imgSprSff.getXAxis() + air.getXOffSet();
        float y = bg.getPos().getY() - imgSprSff.getYAxis() + air.getYOffSet();
        Trans trans = bg.getBgTrans();
        if (air.type == TypeBlit.ASD) {
            trans = Trans.ADD;
        }
        drawTileXY(
                md,
                img,
                bg,
                x,
                y,
                moveX,
                moveY,
                xStartForAll,
                trans,
                air.isMirrorH(),
                air.isMirrorV());
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
        int imgW = img.getWidth();
        int imgH = img.getHeight();
        if (imgW <= 0 || imgH <= 0) {
            return;
        }
        for (int v = 0; v < imgH; ++v) {
            float scaleX =
                    bg.getXscale().getX()
                            + (v * ((bg.getXscale().getY() - bg.getXscale().getX()) / imgH));
            float deltaX = scaleX * bg.getDelta().getX();
            float x1 = x + deltaX * moveX + xStartForAll;
            float y2 = y + moveY * bg.getDelta().getY() + v;
            if (!bg.isEnable()) {
                continue;
            }
            // Dest width for this scanline (perspective). Tile so camera scroll never gaps.
            float stripW = Math.max(1f, imgW * Math.abs(scaleX));
            float startX = x1;
            // Walk left until the strip starts off-screen left.
            while (startX > 0f) {
                startX -= stripW;
            }
            while (startX < SCREEN_W) {
                drawImageRegion(
                        md,
                        bg.getTrans(),
                        img,
                        startX,
                        startX + stripW,
                        y2,
                        y2 + 1,
                        0,
                        imgW,
                        v,
                        v + 1);
                startX += stripW;
            }
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
        int xTile = (int) bg.getTile().getX();
        float tileSpacingX =
                bg.getTilespacing() == null
                        ? img.getWidth()
                        : bg.getTilespacing().getX() == 0 ? img.getWidth() : bg.getTilespacing().getX();
        float tileSpacingY =
                bg.getTilespacing() == null
                        ? img.getHeight()
                        : bg.getTilespacing().getY() == 0
                                ? img.getHeight()
                                : bg.getTilespacing().getY();
        if (tileSpacingX < 1f) {
            tileSpacingX = Math.max(1, img.getWidth());
        }
        if (tileSpacingY < 1f) {
            tileSpacingY = Math.max(1, img.getHeight());
        }
        float deltaY = bg.getDelta().getY();
        float deltaX = bg.getDelta().getX();

        if (xTile == 0) {
            float startPosX = x + moveX * deltaX + xStartForAll;
            float yDraw = y + moveY * deltaY;
            if (bg.getTile().getY() > 0) {
                yDraw = (y + moveY * deltaY) % tileSpacingY;
            }
            drawImage(md, trans, img, startPosX, yDraw, flipH, flipV);
            drawTileY(md, img, bg, startPosX, yDraw, moveY, tileSpacingY, deltaY, trans, flipH, flipV);
            return;
        }

        // Infinite / N-tile: fill the screen so camera scroll never leaves gaps.
        float startPosX = (x + moveX * deltaX + xStartForAll) % tileSpacingX;
        if (startPosX > 0) {
            startPosX -= tileSpacingX;
        }
        while (startPosX < SCREEN_W) {
            float yDraw = y + moveY * deltaY;
            if (bg.getTile().getY() > 0) {
                yDraw = (y + moveY * deltaY) % tileSpacingY;
            }
            drawImage(md, trans, img, startPosX, yDraw, flipH, flipV);
            drawTileY(md, img, bg, startPosX, yDraw, moveY, tileSpacingY, deltaY, trans, flipH, flipV);
            startPosX += tileSpacingX;
        }
    }

    private static void drawTileY(
            MugenDrawer md,
            ImageContainer img,
            BG bg,
            float x,
            float y,
            float moveY,
            float tileSpacingY,
            float deltaY,
            Trans trans,
            boolean flipH,
            boolean flipV) {
        int yTile = (int) bg.getTile().getY();
        if (yTile == 1) {
            float startPosY = y + tileSpacingY;
            while (startPosY < SCREEN_H) {
                drawImage(md, trans, img, x, startPosY, flipH, flipV);
                startPosY += tileSpacingY;
            }
            startPosY = y - tileSpacingY;
            while (startPosY + img.getHeight() > 0) {
                drawImage(md, trans, img, x, startPosY, flipH, flipV);
                startPosY -= tileSpacingY;
            }
        } else if (yTile > 1) {
            float startPosY = (y + moveY) % tileSpacingY;
            int remain = yTile - 1;
            while (startPosY < SCREEN_H && remain > 0) {
                drawImage(md, trans, img, x, startPosY + tileSpacingY * deltaY, flipH, flipV);
                startPosY += img.getHeight();
                remain--;
            }
        }
    }

    private static void drawImage(
            MugenDrawer md, Trans trans, ImageContainer img, float x, float y, boolean flipH, boolean flipV) {
        DrawProperties dp = new DrawProperties(x, y, flipH, flipV, img);
        dp.setTrans(trans);
        md.draw(dp);
    }

    private static void drawImageRegion(
            MugenDrawer md,
            Trans trans,
            ImageContainer img,
            float xLeftDst,
            float xRightDst,
            float yTopDst,
            float yBottomDst,
            float xLeftSrc,
            float xRightSrc,
            float yTopSrc,
            float yBottomSrc) {
        // Match StageBackgroundRender / DrawProperties(xl,xr,yt,yb, xlSrc,xrSrc,ytSrc,ybSrc, ...)
        DrawProperties dp =
                new DrawProperties(
                        xLeftDst,
                        xRightDst,
                        yTopDst,
                        yBottomDst,
                        xLeftSrc,
                        xRightSrc,
                        yTopSrc,
                        yBottomSrc,
                        false,
                        false,
                        img);
        dp.setTrans(trans);
        md.draw(dp);
    }
}
