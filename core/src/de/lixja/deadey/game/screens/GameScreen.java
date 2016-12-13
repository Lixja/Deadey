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

import com.badlogic.gdx.Screen;
import de.lixja.deadey.Deadey;
import de.lixja.deadey.game.objects.Player;
import de.lixja.deadey.game.utils.GameRenderer;
import de.lixja.deadey.game.utils.GameUpdater;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class GameScreen implements Screen {

    private Deadey game;
    private GameRenderer gr;
    private GameUpdater gu;

    private float runTime;

    private int gameWidth, gameHeight;

    private Player player;

    public GameScreen(Deadey game) {
        this.gameHeight = game.getGameWidth();
        this.gameWidth = game.getGameHeight();
        gu = new GameUpdater(game);
        gr = new GameRenderer(game, gu);
    }

    public GameScreen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        gu.update_g(delta);
        gr.render_g(delta);
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
