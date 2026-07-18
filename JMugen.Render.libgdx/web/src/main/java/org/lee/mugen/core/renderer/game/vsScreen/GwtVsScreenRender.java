package org.lee.mugen.core.renderer.game.vsScreen;

import org.lee.mugen.core.gameSelect.GwtGameVsScreen;
import org.lee.mugen.core.renderer.game.fight.BaseRender;
import org.lee.mugen.core.renderer.game.system.BackgroundRender;
import org.lee.mugen.fight.section.elem.FontType;
import org.lee.mugen.fight.section.elem.Type;
import org.lee.mugen.fight.select.GwtCharacters;
import org.lee.mugen.fight.system.MugenSystem;
import org.lee.mugen.fight.system.VsScreen;
import org.lee.mugen.fight.system.elem.GwtPlayerVsScreen;
import org.lee.mugen.geom.MugenPoint;
import org.lee.mugen.renderer.DrawProperties;
import org.lee.mugen.renderer.GraphicsWrapper;
import org.lee.mugen.renderer.ImageContainer;
import org.lee.mugen.renderer.MugenDrawer;
import org.lee.mugen.sprite.entity.PointF;

/** GWT versus screen: animated BG, big portraits and names. */
public class GwtVsScreenRender extends BaseRender {

    private final BackgroundRender backgroundRender;
    private final GwtGameVsScreen gameVsScreen;

    public GwtVsScreenRender(GwtGameVsScreen gameVsScreen) {
        this.gameVsScreen = gameVsScreen;
        backgroundRender = new BackgroundRender(MugenSystem.getInstance().getVersusBackground());
    }

    @Override
    public void render() {
        MugenDrawer md = GraphicsWrapper.getInstance();
        MugenSystem ms = MugenSystem.getInstance();
        VsScreen vsScreen = ms.getVsScreen();
        if (vsScreen == null) {
            return;
        }

        if (vsScreen.getPhase() == VsScreen.ENTER && vsScreen.getFadein() != null) {
            float alpha =
                    (float) vsScreen.getFadein().getTime() / vsScreen.getFadein().getOriginalTime();
            md.setAlpha(1f - alpha);
        } else if (vsScreen.getPhase() == VsScreen.LEAVE && vsScreen.getFadeout() != null) {
            float alpha =
                    (float) vsScreen.getFadeout().getTime() / vsScreen.getFadeout().getOriginalTime();
            md.setAlpha(alpha);
        } else if (vsScreen.getPhase() == VsScreen.END) {
            md.setAlpha(0f);
        } else {
            md.setAlpha(1f);
        }

        backgroundRender.render();

        GwtCharacters characters = ms.getFiles().getGwtSelect().getCharacters();
        if (characters == null) {
            return;
        }

        drawPlayer(md, characters, vsScreen.getP1(), gameVsScreen.getPlayerName(0));
        drawPlayer(md, characters, vsScreen.getP2(), gameVsScreen.getPlayerName(1));
    }

    private void drawPlayer(
            MugenDrawer md, GwtCharacters characters, GwtPlayerVsScreen slot, String charId) {
        if (slot == null || charId == null) {
            return;
        }
        ImageContainer portrait = characters.getBigPortrait(charId);
        if (portrait == null) {
            return;
        }

        MugenPoint pos = slot.getPos().copy();
        boolean facing = slot.getFacing() == -1;
        float fy = 140f / portrait.getHeight();
        float fx = 120f / portrait.getWidth();
        PointF scale = slot.getScale().copy();
        scale.setX(scale.getX() * fx);
        scale.setY(scale.getY() * fy);
        if (facing) {
            pos.x -= portrait.getWidth() * scale.getX();
        }
        DrawProperties dp = new DrawProperties(pos.x, pos.y, facing, false, portrait);
        dp.setXScaleFactor(scale.getX());
        dp.setYScaleFactor(scale.getY());
        md.draw(dp);

        Type nameType = slot.getName();
        if (nameType != null) {
            Type copy = nameType.copy();
            if (copy.getType() instanceof FontType) {
                FontType font = (FontType) copy.getType();
                font.setText(characters.getDisplayName(charId));
                render(md, copy.getPos(), copy);
            }
        }
    }
}
