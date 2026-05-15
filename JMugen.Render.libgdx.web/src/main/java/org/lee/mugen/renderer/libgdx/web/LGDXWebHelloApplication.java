package org.lee.mugen.renderer.libgdx.web;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;

/**
 * Minimal LibGDX/GWT hello world. To use it, set the GWT entry point in
 * {@code JMugenWeb.gwt.xml} to this class instead of {@link LGDXWebApplication}.
 */
public class LGDXWebHelloApplication extends GwtApplication {

    @Override
    public GwtApplicationConfiguration getConfig() {
        return new GwtApplicationConfiguration(480, 320);
    }

    @Override
    public ApplicationListener createApplicationListener() {
        return new ApplicationAdapter() {

            private SpriteBatch batch;
            private BitmapFont font;

            @Override
            public void create() {
                batch = new SpriteBatch();
                font = new BitmapFont();
                font.setColor(1f, 1f, 1f, 1f);
                Gdx.app.log("LGDXWebHelloApplication", "Hello World");
                // index.html keeps a "Carregando..." overlay above the canvas; hide it once GL is up.
                Scheduler.get()
                    .scheduleDeferred(
                        new Scheduler.ScheduledCommand() {
                            @Override
                            public void execute() {
                                Element el =
                                    Document.get().getElementById("jmugen-status");
                                if (el != null) {
                                    el.getStyle().setDisplay(Style.Display.NONE);
                                }
                            }
                        }
                    );
            }

            @Override
            public void render() {
                Gdx.gl.glClearColor(0.1f, 0.15f, 0.22f, 1f);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                batch.begin();
                font.getData().setScale(2f);
                font.draw(batch, "Hello World", 40f, 200f);
                batch.end();
            }

            @Override
            public void dispose() {
                if (font != null) {
                    font.dispose();
                }
                if (batch != null) {
                    batch.dispose();
                }
            }
        };
    }
}
