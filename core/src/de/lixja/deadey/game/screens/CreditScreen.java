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
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import de.lixja.deadey.Deadey;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class CreditScreen implements Screen {

    private Deadey game;
    private BitmapFont font;
    private SpriteBatch batcher;
    private OrthographicCamera cam;

    private LinkedList<String> credits;
    private LinkedList<Vector2> positions;
    private Vector2 speed;
    private boolean left = false;

    public CreditScreen(Deadey game) {
        this.game = game;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, game.getGameWidth(), game.getGameHeight());
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Deadey.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 18;
        parameter.color = Color.WHITE;
        parameter.flip = true;
        font = generator.generateFont(parameter);
        generator.dispose();

        credits = new LinkedList<String>();
        FileHandle file = Gdx.files.internal("credits.xml");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file.file());
            doc.getDocumentElement().normalize();
            NodeList memberList = doc.getElementsByTagName("member");
            for (int i = 0; i < memberList.getLength(); i++) {
                Node member = memberList.item(i);
                Element element = (Element) member;
                credits.add(element.getElementsByTagName("job").item(0).getTextContent() + ": " + element.getElementsByTagName("name").item(0).getTextContent().toUpperCase());
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CreditScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(CreditScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreditScreen.class.getName()).log(Level.SEVERE, null, ex);
        }


        positions = new LinkedList<Vector2>();
        for (String credit : credits) {
            float y;

            y = (float) Math.random() * 200 + 50;

            for (int i = 0; i < positions.size(); i++) {
                if (!((y >= (positions.get(i).y + 50)) || (y <= (positions.get(i).y - 50)))) {
                    i = 0;
                    y = (float) Math.random() * 200;
                }
            }

            if (left) {
                positions.add(new Vector2(0, y));
            } else {
                positions.add(new Vector2(300, y));
            }
            left = !left;
        }
        left = false;

        speed = new Vector2(60, 0);



    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.setProjectionMatrix(cam.combined); //or your matrix to draw GAME WORLD, not UI
        batcher.begin();

        for (int i = 0; i < positions.size(); i++) {
            font.draw(batcher, credits.get(i), positions.get(i).x, positions.get(i).y);
            if (left) {
                positions.get(i).x += speed.x * delta;
            } else {
                positions.get(i).x -= speed.x * delta;

            }
            left = !left;
        }
        batcher.end();
        left = false;
        if (Gdx.input.isTouched()) {
            game.setScreen(new MenuScreen(game));
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
