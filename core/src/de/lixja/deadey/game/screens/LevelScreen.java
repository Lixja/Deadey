/*
 * Copyright (C) 2016 Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.lixja.deadey.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import de.lixja.deadey.Deadey;
import de.lixja.deadey.game.handler.DClickListener;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class LevelScreen implements Screen {

    Skin skin;
    Stage stage;
    Deadey game;

    /*
            this.game = game;
        for (FileHandle levels : Gdx.files.internal("world").list()) {
            System.out.println(levels.file().getAbsolutePath());
            levels.list()
        }
     */

    public LevelScreen(Deadey game) {
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        BitmapFont font = new BitmapFont();
        BitmapFont headerFont = new BitmapFont();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Deadey.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        parameter.color = Color.WHITE;
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.size = 39;
        parameter1.color = Color.WHITE;
        font = generator.generateFont(parameter);
        headerFont = generator.generateFont(parameter1);
        generator.dispose();
        skin = new Skin();
        skin.add("default", font);
        skin.add("headerfont", headerFont);


        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("headerfont");
        skin.add("default", labelStyle);

        Label title = new Label("WORLDS", skin);
        title.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, (Gdx.graphics.getHeight() / 5) * 4);
        stage.addActor(title);

        FileHandle worlds[] = Gdx.files.internal("world").list();
        for (int i = 0; i < worlds.length; i++) {
            TextButton tmp = new TextButton(worlds[i].name().toUpperCase(), skin); // Use the initialized skin
        tmp.setPosition((Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8) * i, (Gdx.graphics.getHeight() / 2));
            tmp.addListener(new DClickListener(worlds[i]) {
            public void clicked(InputEvent event, float x, float y) {
                showLevels(file);
            }
        });
        stage.addActor(tmp);
        }
    }

    public void showLevels(FileHandle world) {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        BitmapFont font = new BitmapFont();
        BitmapFont headerFont = new BitmapFont();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Deadey.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        parameter.color = Color.WHITE;
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.size = 39;
        parameter1.color = Color.WHITE;
        font = generator.generateFont(parameter);
        headerFont = generator.generateFont(parameter1);
        generator.dispose();
        skin = new Skin();
        skin.add("default", font);
        skin.add("headerfont", headerFont);


        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("headerfont");
        skin.add("default", labelStyle);

        Label title = new Label(world.name().toUpperCase(), skin);
        title.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, (Gdx.graphics.getHeight() / 5) * 4);
        stage.addActor(title);

        FileHandle levels[] = world.list();
        for (int i = 0; i < levels.length; i++) {
            TextButton tmp = new TextButton(levels[i].nameWithoutExtension().toUpperCase(), skin); // Use the initialized skin
            tmp.setPosition((Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8) * i, (Gdx.graphics.getHeight() / 2));
            tmp.addListener(new DClickListener(levels[i]) {
                public void clicked(InputEvent event, float x, float y) {
                    stage.dispose();
                    Gdx.input.setInputProcessor(null);
                    game.setScreen(new GameScreen(game, file.readString()));
                }
            });
            stage.addActor(tmp);
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            stage.getCamera().translate(250 * delta, 0, 0);
        } else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            stage.getCamera().translate(-250 * delta, 0, 0);

        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

}
