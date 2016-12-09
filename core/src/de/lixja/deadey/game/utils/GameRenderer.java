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
import de.lixja.deadey.game.objects.Coin;
import de.lixja.deadey.game.objects.Enemy;
import de.lixja.deadey.game.objects.Shot;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class GameRenderer {

    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;
    private GameUpdater gu;
    private MapRenderer mrenderer;

    public GameRenderer(Deadey game, GameUpdater gu) {
        this.gu = gu;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, game.getGameWidth(), game.getGameHeight());

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        shapeRenderer.setAutoShapeType(true);

        mrenderer = new MapRenderer(shapeRenderer, cam);

    }

    public void render_g(float delta) {
        Gdx.gl.glClearColor(0, 255, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.translate(gu.getCamera().getToMoveX(), gu.getCamera().getToMoveY());
        cam.update();
        //moveCamera(100 * gu.getTime());

        mrenderer.render(gu.getStage1());

        batcher.begin();
        batcher.setProjectionMatrix(cam.combined);


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
            } else
                if (gu.getPlayer().isLeft()) {
                batcher.draw(AssetLoader.player_runs_left.getKeyFrame(gu.getPlayer().getTime(), true), gu.getPlayer().getPosition().x, gu.getPlayer().getPosition().y);
            } else if (!gu.getPlayer().isLeft()) {
                batcher.draw(AssetLoader.player_runs_right.getKeyFrame(gu.getPlayer().getTime(), true), gu.getPlayer().getPosition().x, gu.getPlayer().getPosition().y);
            }
        } else
            if (!gu.getPlayer().isFire()) {
                if (gu.getPlayer().isLeft()) {
            batcher.draw(AssetLoader.player_fly_left.getKeyFrame(gu.getPlayer().getTime(), false), gu.getPlayer().getPosition().x, gu.getPlayer().getPosition().y);
        } else {
            batcher.draw(AssetLoader.player_fly_right.getKeyFrame(gu.getPlayer().getTime(), false), gu.getPlayer().getPosition().x, gu.getPlayer().getPosition().y);
                }
            } else {
                if (gu.getPlayer().isLeft()) {
                    batcher.draw(AssetLoader.player_fly_fire_left.getKeyFrame(gu.getPlayer().getTime(), true), gu.getPlayer().getPosition().x, gu.getPlayer().getPosition().y);
                } else {
                    batcher.draw(AssetLoader.player_fly_fire_right.getKeyFrame(gu.getPlayer().getTime(), true), gu.getPlayer().getPosition().x, gu.getPlayer().getPosition().y);
                }
            }

        //Enemy
        for (Enemy e : gu.getEnemy()) {
            if (e.isLeft()) {
                batcher.draw(AssetLoader.enemy_runs_left.getKeyFrame(e.getTime(), true), e.getPosition().x, e.getPosition().y);
            } else {
                batcher.draw(AssetLoader.enemy_runs_right.getKeyFrame(e.getTime(), true), e.getPosition().x, e.getPosition().y);
            }
        }
        //Shot
        for (Shot s : gu.getShots()) {
            if (s.isAvailable()) {
                if (s.isToLeft()) {
                    batcher.draw(AssetLoader.shot_left.getKeyFrame(s.getTime(), true), s.getPosition().x, s.getPosition().y);
                } else {
                    batcher.draw(AssetLoader.shot_right.getKeyFrame(s.getTime(), true), s.getPosition().x, s.getPosition().y);
                }
            }
        }

        for (Coin c : gu.getCoins()) {
            batcher.draw(AssetLoader.coin.getKeyFrame(c.getTime(), true), c.getPosition().x, c.getPosition().y);
        }
        batcher.end();
    }

    public void moveCamera(float speedX) {
        cam.translate(speedX, 0);
        cam.update();
    }
}
