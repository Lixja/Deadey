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
package de.lixja.deadey.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.lixja.deadey.Deadey;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class GameRenderer {

    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;
    private GameUpdater gu;

    public GameRenderer(Deadey game, GameUpdater gu) {
        this.gu = gu;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, game.getGameWidth(), game.getGameHeight());

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }

    public void render_g(float delta) {
        Gdx.gl.glClearColor(0, 255, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();

        //Player
        if (!gu.getPlayer().isFly()) {
            if (!gu.getPlayer().isMoving()) {
                if (!gu.getPlayer().isFire()) {
                    batcher.draw(AssetLoader.player_stands, gu.getPlayer().getPosition().x, gu.getPlayer().getPosition().y);
                } else if (!gu.getPlayer().isLeft()) {
                    batcher.draw(AssetLoader.player_fire_right.getKeyFrame(gu.getPlayer().getTime(), true), gu.getPlayer().getPosition().x, gu.getPlayer().getPosition().y);
                } else if (gu.getPlayer().isLeft()) {
                    batcher.draw(AssetLoader.player_fire_left.getKeyFrame(gu.getPlayer().getTime(), true), gu.getPlayer().getPosition().x, gu.getPlayer().getPosition().y);
                }
            } else if (gu.getPlayer().isLeft()) {
                batcher.draw(AssetLoader.player_runs_left.getKeyFrame(gu.getPlayer().getTime(), true), gu.getPlayer().getPosition().x, gu.getPlayer().getPosition().y);
            } else if (!gu.getPlayer().isLeft()) {
                batcher.draw(AssetLoader.player_runs_right.getKeyFrame(gu.getPlayer().getTime(), true), gu.getPlayer().getPosition().x, gu.getPlayer().getPosition().y);
            }
        } else if (gu.getPlayer().isLeft()) {
            batcher.draw(AssetLoader.player_fly_left, gu.getPlayer().getPosition().x, gu.getPlayer().getPosition().y);
        } else {
            batcher.draw(AssetLoader.player_fly_right, gu.getPlayer().getPosition().x, gu.getPlayer().getPosition().y);
        }

        //Enemy
        for (int i = 0; i < gu.getEnemy().length; i++) {
            if (gu.getEnemy()[i].isLeft()) {
            batcher.draw(AssetLoader.enemy_runs_left.getKeyFrame(gu.getEnemy()[i].getTime(), true), gu.getEnemy()[i].getPosition().x, gu.getEnemy()[i].getPosition().y);
            } else {
                batcher.draw(AssetLoader.enemy_runs_right.getKeyFrame(gu.getEnemy()[i].getTime(), true), gu.getEnemy()[i].getPosition().x, gu.getEnemy()[i].getPosition().y);
            }
        }
        batcher.end();
    }
}
