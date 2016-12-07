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
package de.lixja.deadey.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import de.lixja.deadey.game.utils.AssetLoader;
import de.lixja.deadey.game.utils.GameUpdater;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Player extends GameObject {

    private Vector2 speed;
    private boolean moving = false;
    private boolean left = false;
    private boolean fire = false;
    private boolean fly = false;
    private boolean fall = false;
    private boolean portal = false;

    private float time;
    private float portalreloader;
    private float shotloader;

    private GameUpdater gu;


    public Player(float x, float y, int width, int height, GameUpdater gu) {
        super(x, y, width, height);
        speed = new Vector2(75, 100);
        this.gu = gu;
    }

    public void update(float delta) {
        moving = false;
        fire = false;
        fly = false;
        width = AssetLoader.player_stands.getRegionWidth();


        if (portalreloader >= 5) {
            portal = true;
            portalreloader = 0;
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            if (position.x <= 200) {
                position.x += speed.x * delta;
            }
            moving = true;
            left = false;
            width = AssetLoader.player_left.getRegionWidth();

        } else
            if (Gdx.input.isKeyPressed(Keys.A)) {
                if (position.x >= 0) {
                    position.x -= speed.x * delta;
                }
            moving = true;
            left = true;
            width = AssetLoader.player_left.getRegionWidth();
        } else {
            if (Gdx.input.isKeyPressed(Keys.SPACE)) {
            fire = true;
            shotloader += delta;
                if (shotloader >= AssetLoader.player_fire_left.getAnimationDuration() - 0.15f) {
                shotloader = 0;
                gu.createShot(left);
            }
            } else {
                shotloader = 0;
                }
            }
        if (!fall) {
            if (Gdx.input.isKeyPressed(Keys.W)) {
                if (position.y > 10) {
                    position.y -= speed.y * delta;
                    fly = true;
                } else {
                    fly = false;
                    fall = true;
                }
            } else if (position.y < 100) {
                fall = true;
            }
        } else if (fall) {
            position.y += speed.y * delta;
            if (position.y >= 100) {
                fall = false;
            }
        }
        if (Gdx.input.isKeyPressed(Keys.Q) && portal) {
            position.x = Float.parseFloat("" + Math.random() * (300 - width));
            position.y = Float.parseFloat("" + Math.random() * 100 + 50);
            portal = false;
        }

        portalreloader += delta;
        time += delta;
    }

    public boolean isMoving() {
        return moving;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isFire() {
        return fire;
    }

    public boolean isFly() {
        return fly;
    }

    public float getTime() {
        return time;
    }

}
